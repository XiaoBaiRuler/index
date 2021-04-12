package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.PublicNodeService;
import net.xiaobais.xiaobai.service.UserService;
import net.xiaobais.xiaobai.vo.PublicUserVo;
import net.xiaobais.xiaobai.vo.SimpleNodeVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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

    @CrossOrigin
    @ApiOperation("获取个人的公开展示信息")
    @GetMapping("/public/getPublicUserVo/{userId}")
    @ResponseBody
    public PublicUserVo getPublicUserVo(@PathVariable Integer userId){
        return userService.getPublicUserVo(userId);
    }

    @ApiOperation("跳转公开个人主页")
    @GetMapping("/public/user/{userId}")
    public String toPublicUser(@PathVariable Integer userId, Model model){
        model.addAttribute("userId", userId);
        model.addAttribute("mostCollect", nodeToSimpleNodeVo(nodeService.findNodeByTopCollect(5)));
        model.addAttribute("mostStar", nodeToSimpleNodeVo(nodeService.findNodeByTopStar(5)));
        return "publicUser";
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
