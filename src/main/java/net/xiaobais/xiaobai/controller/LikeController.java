package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.service.LikeService;
import net.xiaobais.xiaobai.service.NodeService;
import net.xiaobais.xiaobai.utils.JwtUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
    private NodeService nodeService;

    @ApiOperation("给当前用户添加点赞数")
    @GetMapping("/addNodeLike")
    @ResponseBody
    public String addNodeLike(@RequestParam Integer nodeId, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token = "";
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                token = cookie.getValue();
                Integer userId = JwtUtils.getUserId(token);
                // 检查该用户是否点赞过该节点
                if (!likeService.isLike(userId, nodeId)){
                    nodeService.addLike(nodeId);
                    likeService.addLike(userId, nodeId);
                    return "1";
                }
            }
        }
        return "0";
    }
}
