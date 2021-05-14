package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Blog;
import net.xiaobais.xiaobai.model.Iterator;
import net.xiaobais.xiaobai.model.Map;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.*;
import net.xiaobais.xiaobai.vo.SimpleNodeVo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/3/11 10:43
 * @Version 1.0
 */
@Api(tags = "IteratorController")
@Controller
@RequestMapping("/person/public")
public class IteratorController {

    @Resource
    private PublicNodeService nodeService;
    @Resource
    private BlogService blogService;
    @Resource
    private MapService mapService;
    @Resource
    private IteratorService iteratorService;
    @Resource
    private NoticeService noticeService;
    @Resource
    private CacheService cacheService;

    private static final int SIZE = 1000;
    private static final String PUBLIC_ID = "/public/getNodeMind";

    @ApiOperation("跳转添加迭代节点页面")
    @GetMapping("/toAddIterator/{nodeId}")
    public String toAddIterator(@PathVariable Integer nodeId, Model model){
        Node node = nodeService.findNodeById(nodeId);
        Blog blog = blogService.findBlogById(node.getBlogId());
        model.addAttribute("nodeId", nodeId);
        model.addAttribute("html", blog.getBlogContent());
        if (blog.getBlogContent() != null && blog.getBlogContent().length() > SIZE) {
            model.addAttribute("flag", false);
        }
        else {
            model.addAttribute("flag", true);
        }
        model.addAttribute("mostCollect", nodeToSimpleNodeVo(nodeService.findNodeByTopCollect(5)));
        model.addAttribute("mostStar", nodeToSimpleNodeVo(nodeService.findNodeByTopStar(5)));
        return "addIterator";
    }

    @ApiOperation("添加迭代节点和迭代关系")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/addIterator")
    @ResponseBody
    public void addIterator(Integer nodeId, String html,
                            String map, String username,
                            String reason, Integer userId) throws Exception {
        if (nodeId == 1){
            return;
        }
        if (reason == null || "".equals(reason)){
            return;
        }
        Node node = nodeService.findNodeById(nodeId);
        Integer nextId = node.getUserId();
        // 新建一个blog
        Blog blog = blogService.findBlogById(node.getBlogId());
        blog.setBlogId(null);
        blog.setBlogContent(html);
        int i = blogService.insertBlog(blog);
        if (i == -1){
            throw new Exception("添加博客失败");
        }
        // 新建一个map
        Map m = mapService.findMapById(node.getMapId());
        m.setMapName(username);
        m.setMapId(null);
        m.setMapData(map);
        int j = mapService.insertMap(m);
        if (j == -1){
            throw new Exception("添加思维导图内容失败");
        }
        // 新建一个node
        node.setUserId(userId);
        node.setNodeName(username + " => " + node.getNodeName());
        node.setBlogId(i);
        node.setMapId(j);
        int k = nodeService.insertNode(node);
        if (k == -1){
            throw new Exception("添加节点失败");
        }
        // 添加迭代关系
        Iterator iterator = new Iterator();
        iterator.setIteratorId(k);
        iterator.setIteratorName(username + " => " + node.getNodeName());
        iterator.setNodeId(nodeId);
        iterator.setIteratorReason(reason);
        int l = iteratorService.insertIterator(iterator);
        if (l == -1){
            throw new Exception("添加迭代关系失败");
        }
        // 添加迭代通知
        String message = username + "想迭代节点" + node.getNodeName() + ",理由:" + reason;
        if (!noticeService.addIteratorNotice(userId, nextId, k, nodeId, message)){
            throw new Exception("迭代通知创建失败");
        }
        cacheService.deleteAllMindListByKey(PUBLIC_ID);
    }

    @ApiOperation("展示迭代节点")
    @GetMapping("/iterator/{nodeId}")
    public String getIterator(@PathVariable Integer nodeId, Model model){
        Node node = nodeService.findNodeById(nodeId);
        Blog blog = blogService.findBlogById(node.getBlogId());
        model.addAttribute("nodeId", nodeId);
        model.addAttribute("html", blog.getBlogContent());
        if (blog.getBlogContent() != null && blog.getBlogContent().length() > SIZE) {
            model.addAttribute("flag", false);
        }
        else {
            model.addAttribute("flag", true);
        }
        model.addAttribute("mostCollect", nodeToSimpleNodeVo(nodeService.findNodeByTopCollect(5)));
        model.addAttribute("mostStar", nodeToSimpleNodeVo(nodeService.findNodeByTopStar(5)));
        return "publicIterator";
    }

    private List<SimpleNodeVo> nodeToSimpleNodeVo(List<Node> nodes){
        List<SimpleNodeVo> simpleNodeVos = new ArrayList<>(nodes.size());
        nodes.forEach(node -> {
                    SimpleNodeVo vo = new SimpleNodeVo();
                    vo.setUrl("/public/node/" + node.getNodeId());
                    vo.setTitle(node.getNodeName());
                    simpleNodeVos.add(vo);
                }
        );
        return simpleNodeVos;
    }
}
