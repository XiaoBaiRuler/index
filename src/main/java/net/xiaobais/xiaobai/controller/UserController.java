package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.model.User;
import net.xiaobais.xiaobai.service.PublicNodeService;
import net.xiaobais.xiaobai.service.UserService;
import net.xiaobais.xiaobai.utils.JwtUtils;
import net.xiaobais.xiaobai.vo.PublicUserVo;
import net.xiaobais.xiaobai.vo.SimpleNodeVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/4/11 17:43
 * @Version 1.0
 */
@Api(tags = "UserController")
@Controller
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private PublicNodeService nodeService;

    @ApiOperation("跳转公开个人主页")
    @GetMapping("/public/user/{userId}")
    public String toPublicUser(@PathVariable Integer userId, Model model){
        model.addAttribute("userId", userId);
        model.addAttribute("mostCollect", nodeToSimpleNodeVo(nodeService.findNodeByTopCollect(5)));
        model.addAttribute("mostStar", nodeToSimpleNodeVo(nodeService.findNodeByTopStar(5)));
        return "publicUser";
    }

    @ApiOperation("跳转私有个人主页")
    @GetMapping("/private/user/{userId}")
    public String toPrivateUser(@PathVariable Integer userId, Model model, HttpServletRequest request){

        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "redirect:/toLogin";
        }
        if (!JwtUtils.getUserId(cookies[0].getValue()).equals(userId)){
            return "/error/403";
        }
        model.addAttribute("userId", userId);
        model.addAttribute("mostCollect", nodeToSimpleNodeVo(nodeService.findNodeByTopCollect(5)));
        model.addAttribute("mostStar", nodeToSimpleNodeVo(nodeService.findNodeByTopStar(5)));
        return "privateUser";
    }


    @CrossOrigin
    @ApiOperation("获取个人的公开展示信息")
    @GetMapping("/public/getPublicUserVo/{userId}")
    @ResponseBody
    public PublicUserVo getPublicUserVo(@PathVariable Integer userId){
        return userService.getPublicUserVo(userId);
    }

    @ApiOperation("更改用户简介")
    @PostMapping("/person/public/changeDesc")
    @ResponseBody
    public String changeDescription(String desc, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "#用户未登录";
        }
        Integer userId = JwtUtils.getUserId(cookies[0].getValue());
        User user = new User();
        user.setUserId(userId);
        user.setUserDesc(desc);
        return userService.updateUser(user) == -1 ? "#修改简介失败" : "修改成功";
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
