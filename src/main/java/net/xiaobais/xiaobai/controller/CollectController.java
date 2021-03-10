package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.service.CollectService;
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
 * @Date 2021/3/4 17:31
 * @Version 1.0
 */
@Api(tags = "CollectController")
@Controller
public class CollectController {

    @Resource
    private NodeService nodeService;
    @Resource
    private CollectService collectService;

    @ApiOperation("添加收藏")
    @GetMapping("/person/public/addNodeStar")
    @ResponseBody
    public String addNodeCount(@RequestParam Integer nodeId, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String token = "";
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                token = cookie.getValue();
                Integer userId = JwtUtils.getUserId(token);
                // 检查该用户是否点赞过该节点
                if (!collectService.isCollect(userId, nodeId)){
                    nodeService.addCount(nodeId);
                    collectService.addCollect(userId, nodeId);
                    return "1";
                }
            }
        }
        return "0";
    }

}
