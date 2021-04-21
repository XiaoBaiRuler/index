package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.CacheService;
import net.xiaobais.xiaobai.service.LikeService;
import net.xiaobais.xiaobai.service.PublicNodeService;
import net.xiaobais.xiaobai.utils.JwtUtils;
import net.xiaobais.xiaobai.vo.SimpleNodeVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author xiaobai
 * @Date 2021/3/4 17:25
 * @Version 1.0
 */
@Api(tags = "LikeController")
@Controller
public class LikeController {

    @Resource
    private LikeService likeService;
    @Resource
    private PublicNodeService publicNodeService;
    @Resource
    private CacheService cacheService;

    private static final String NODE_PREFIX = "/public/node/";
    private static final String TOP_LiKE_CACHE = "/public/getTopStar";

    @ApiOperation("添加点赞")
    @GetMapping("/person/public/addNodeLike")
    @ResponseBody
    public String addNodeLike(@RequestParam Integer nodeId, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "0";
        }
        Integer userId = JwtUtils.getUserId(cookies[0].getValue());
        if (!likeService.isLike(userId, nodeId)){
            publicNodeService.addLike(nodeId);
            likeService.addLike(userId, nodeId);
            cacheService.deleteAllSimpleNodeVoList(TOP_LiKE_CACHE);
            return "1";
        }
        return "0";
    }

    @ApiOperation("获取最高点赞的n个博客")
    @GetMapping("/public/getTopStar")
    @ResponseBody
    public List<SimpleNodeVo> findNodeByTopCollect(Integer n){

        List<SimpleNodeVo> listCache = cacheService.getSimpleNodeVoListByKey(TOP_LiKE_CACHE + n);
        if (listCache != null){
            return listCache;
        }
        else{
            List<Node> nodes = publicNodeService.findNodeByTopStar(n);
            List<SimpleNodeVo> list = new ArrayList<>();
            nodes.stream().filter(Objects::nonNull).forEach(node -> {
                SimpleNodeVo item = new SimpleNodeVo();
                item.setTitle(node.getNodeName());
                item.setUrl(NODE_PREFIX + node.getNodeId());
                list.add(item);
            });
            cacheService.setSimpleNodeVoListByKey(TOP_LiKE_CACHE + n, list);
            return list;
        }
    }
}
