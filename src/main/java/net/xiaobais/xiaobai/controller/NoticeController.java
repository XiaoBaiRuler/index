package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Blog;
import net.xiaobais.xiaobai.model.Map;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.*;
import net.xiaobais.xiaobai.utils.JwtUtils;
import net.xiaobais.xiaobai.vo.PublicNoticeVo;
import net.xiaobais.xiaobai.vo.SimpleNodeVo;
import net.xiaobais.xiaobai.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/4/15 19:29
 * @Version 1.0
 */
@Api(tags = "NoticeController")
@Controller
@RequestMapping("/person/public")
public class NoticeController {

    @Resource
    private NoticeService noticeService;
    @Resource
    private PrivateNodeService privateNodeService;
    @Resource
    private PublicNodeService publicNodeService;
    @Resource
    private BlogService blogService;
    @Resource
    private MapService mapService;
    @Resource
    private PreviousService previousService;
    @Resource
    private NextService nextService;

    private static final String NODE_PREFIX = "/public/node/";

    @ApiOperation("跳转通知管理页面")
    @GetMapping("/toNotice")
    public String toNotice(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "redirect:/toLogin";
        }
        model.addAttribute("userId", JwtUtils.getUserId(cookies[0].getValue()));
        model.addAttribute("mostCollect", nodeToSimpleNodeVo(publicNodeService.findNodeByTopCollect(5)));
        model.addAttribute("mostStar", nodeToSimpleNodeVo(publicNodeService.findNodeByTopStar(5)));
        return "privateNotice";
    }

    @ApiOperation("获取所有发布通知")
    @GetMapping("/getPublicNotice")
    @ResponseBody
    public List<PublicNoticeVo> getAllNotice(@RequestParam Integer pageNumber,
                                             @RequestParam Integer pageSize,
                                             @RequestParam String message,
                                             HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        UserVo user = JwtUtils.getUserVo(cookies[0].getValue());
        if (user.getUserId() == 1){
            return noticeService.getAllPublicNotice(pageNumber, pageSize, message);
        }
        return null;
    }

    @ApiOperation("获取所有发布通知的个数")
    @GetMapping("/getPublicNoticeCount")
    @ResponseBody
    public Integer getPublicNoticeCount(@RequestParam String message){
        return noticeService.getAllPublicNoticeCount(message);
    }

    @ApiOperation("发送发布节点通知")
    @GetMapping("/addPublicNodeNotice")
    @ResponseBody
    public String addPublicNodeNotice(Integer nodeId, Integer rootId, Integer flag,
                                      HttpServletRequest request) throws Exception {

        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "#用户未登录";
        }
        UserVo user = JwtUtils.getUserVo(cookies[0].getValue());
        int i = noticeService.addPublicNodeNotice(user.getUsername(), nodeId,
                user.getUserId(), rootId, flag);
        return i != -1 ? "发送发布通知成功" : "#发送发布通知失败";
    }

    @ApiOperation("处理发布节点通知")
    @GetMapping("/dealPublicNodeNotice/{noticeId}/{nodeId}/{userId}/{rootId}/{flag}")
    @ResponseBody
    public String dealPublicNodeNotice(@PathVariable Integer noticeId, @PathVariable Integer nodeId,
                                       @PathVariable Integer userId, @PathVariable Integer rootId,
                                       @PathVariable Integer flag, HttpServletRequest request) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "#用户未登录";
        }
        UserVo user = JwtUtils.getUserVo(cookies[0].getValue());
        Integer newNodeId = copyPrivateNodeToPublicNode(nodeId, userId, rootId, flag);
        String message = user.getUsername() + "同意你发布的博客作为他的附近节点, 节点链接为" + NODE_PREFIX + newNodeId;
        return noticeService.dealPublicNodeNotice(message, userId, nodeId, newNodeId, noticeId) ?
                "发送通知成功" : "#发送通知失败";
    }

    @ApiOperation("驳回发布节点通知")
    @PostMapping("/errorPublicNodeNotice")
    @ResponseBody
    public String errorPublicNodeNotice(Integer nodeId, Integer userId, String message, Integer noticeId)
            throws Exception {
        return noticeService.errorPublicNodeNotice(nodeId, message, userId, noticeId) ?
                "驳回发布请求成功" : "#驳回发布请求失败";
    }



    @Transactional(rollbackFor = Exception.class)
    public Integer copyPrivateNodeToPublicNode(Integer nodeId, Integer userId, Integer rootId, Integer flag) throws Exception {
        Node node = privateNodeService.findNodeByNodeIdAndIsPrivateAndUserId(nodeId, userId);
        synchronized (this){
            // 创建一个博客
            Blog blog = blogService.findBlogById(node.getBlogId());
            blog.setBlogId(null);
            blog.setUpdateDate(new Date());
            int blogId = blogService.insertBlog(blog);
            if (blogId == -1){
                throw new Exception("复制博客失败");
            }
            // 创建一个思维导图
            Map map = mapService.findMapById(node.getMapId());
            map.setMapId(null);
            int mapId = mapService.insertMap(map);
            if (mapId == -1){
                throw new Exception("复制思维导图失败");
            }
            node.setNodeId(null);
            node.setIsPrivate(false);
            node.setUpdateDate(new Date());
            node.setBlogId(blogId);
            node.setMapId(mapId);
            int newNodeId = publicNodeService.insertNode(node);
            if (newNodeId == -1){
                throw new Exception("复制节点失败");
            }
            if (flag != 1){
                int itemId = rootId;
                rootId = newNodeId;
                newNodeId = itemId;
            }
            int i = previousService.addPrevious(rootId, newNodeId);
            if (i == -1){
                throw new Exception("复制前置关系失败");
            }
            int j = nextService.addNext(newNodeId, rootId);
            if (j == -1){
                throw new Exception("复制后置关系失败");
            }
            return newNodeId;
        }
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