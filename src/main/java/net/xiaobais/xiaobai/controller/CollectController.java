package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Blog;
import net.xiaobais.xiaobai.model.Map;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.*;
import net.xiaobais.xiaobai.utils.JwtUtils;
import net.xiaobais.xiaobai.vo.SimpleNodeVo;
import net.xiaobais.xiaobai.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author xiaobai
 * @Date 2021/3/4 17:31
 * @Version 1.0
 */
@Api(tags = "CollectController")
@Controller
public class CollectController {

    @Resource
    private PublicNodeService publicNodeService;
    @Resource
    private CollectService collectService;
    @Resource
    private PrivateNodeService privateNodeService;
    @Resource
    private BlogService blogService;
    @Resource
    private MapService mapService;
    @Resource
    private PreviousService previousService;
    @Resource
    private NextService nextService;
    @Resource
    private CacheService cacheService;

    private static final String NODE_PREFIX = "/public/node/";
    private static final String TOP_COLLECT_CACHE = "/public/getTopCollect";

    @ApiOperation("添加收藏")
    @GetMapping("/person/public/addNodeStar")
    @ResponseBody
    public String addNodeCount(@RequestParam Integer nodeId, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "0";
        }
        Integer userId = JwtUtils.getUserId(cookies[0].getValue());
        // 检查该用户是否点赞过该节点
        if (!collectService.isCollect(userId, nodeId)){
            publicNodeService.addCount(nodeId);
            collectService.addCollect(userId, nodeId);
            cacheService.deleteAllSimpleNodeVoList(TOP_COLLECT_CACHE);
            return "1";
        }
        return "0";
    }

    @ApiOperation("收藏节点转私有节点")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/person/public/collectToPrivate")
    @ResponseBody
    public String collectToPrivate(Integer nodeId, Integer yourNodeId, Integer flag,
                                   HttpServletRequest request) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "#没有登录";
        }
        UserVo user = JwtUtils.getUserVo(cookies[0].getValue());
        if (user.getUserId() == 1){
            return "#超级管理员: 可以管理所有公开节点(不需要转化)";
        }
        // 判断是否有收藏关系
        if (!collectService.isCollect(user.getUserId(), nodeId)){
            return "#你没有收藏过该节点";
        }
        Node node = publicNodeService.findNodeById(nodeId);
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
            map.setMapAuthor(user.getUsername());
            int mapId = mapService.insertMap(map);
            if (mapId == -1){
                throw new Exception("复制思维导图失败");
            }
            // 创建一个节点
            node.setUserId(user.getUserId());
            node.setNodeId(null);
            node.setIsPrivate(true);
            node.setUpdateDate(new Date());
            node.setCollect(0);
            node.setStar(0);
            node.setBlogId(blogId);
            node.setMapId(mapId);
            int newNodeId = privateNodeService.insertNode(node);
            if (newNodeId == -1){
                throw new Exception("复制节点失败");
            }
            String url = "/private/node/" + newNodeId + "/" + user.getUserId() + "?isUpdate=0";
            // 1为前置节点，0为后置节点
            if (flag != 1){
                int itemId = yourNodeId;
                yourNodeId = newNodeId;
                newNodeId = itemId;
            }
            int i = previousService.addPrevious(yourNodeId, newNodeId);
            if (i == -1){
                throw new Exception("复制前置关系失败");
            }
            int j = nextService.addNext(newNodeId, yourNodeId);
            if (j == -1){
                throw new Exception("复制后置关系失败");
            }
            return url;
        }
    }

    @ApiOperation("获取最高收藏的n个博客")
    @GetMapping("/public/getTopCollect")
    @ResponseBody
    public List<SimpleNodeVo> findNodeByTopCollect(Integer n){
        String key = TOP_COLLECT_CACHE + n;
        List<SimpleNodeVo> cacheList = cacheService.getSimpleNodeVoListByKey(key);
        if (cacheList != null){
            return cacheList;
        }
        else{
            List<Node> nodes = publicNodeService.findNodeByTopCollect(n);
            List<SimpleNodeVo> list = new ArrayList<>();
            nodes.stream().filter(Objects::nonNull).forEach(node -> {
                SimpleNodeVo item = new SimpleNodeVo();
                item.setTitle(node.getNodeName());
                item.setUrl(NODE_PREFIX + node.getNodeId());
                list.add(item);
            });
            cacheService.setSimpleNodeVoListByKey(key, list);
            return list;
        }
    }

}
