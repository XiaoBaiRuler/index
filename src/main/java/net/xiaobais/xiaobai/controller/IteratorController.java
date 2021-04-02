package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Blog;
import net.xiaobais.xiaobai.model.Iterator;
import net.xiaobais.xiaobai.model.Map;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.BlogService;
import net.xiaobais.xiaobai.service.IteratorService;
import net.xiaobais.xiaobai.service.MapService;
import net.xiaobais.xiaobai.service.PublicNodeService;
import net.xiaobais.xiaobai.vo.SimpleNodeVo;
import org.springframework.stereotype.Controller;
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

    private static final int SIZE = 1000;

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
    @PostMapping("/addIterator")
    @ResponseBody
    public void addIterator(Integer nodeId, String html,
                            String map, String username,
                            String reason, Integer userId){
        Node node = nodeService.findNodeById(nodeId);
        // 新建一个blog
        Blog blog = blogService.findBlogById(node.getBlogId());
        blog.setBlogId(null);
        blog.setBlogContent(html);
        int i = blogService.insertBlog(blog);
        // 新建一个map
        Map m = mapService.findMapById(node.getMapId());
        m.setMapName(username);
        m.setMapId(null);
        m.setMapData(map);
        int j = mapService.insertMap(m);
        // 新建一个node
        int k = -1;
        if (i != -1 && j != -1){
            node.setUserId(userId);
            node.setNodeName(username + " => " + node.getNodeName());
            node.setBlogId(i);
            node.setMapId(j);
            k = nodeService.insertNode(node);
        }
        // 添加迭代关系
        if (k != -1){
            Iterator iterator = new Iterator();
            iterator.setIteratorId(k);
            iterator.setIteratorName(username + " => " + node.getNodeName());
            iterator.setNodeId(nodeId);
            iterator.setIteratorReason(reason);
            iteratorService.insertIterator(iterator);
        }
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
