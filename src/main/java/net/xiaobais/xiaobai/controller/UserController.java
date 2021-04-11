package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.service.UserService;
import net.xiaobais.xiaobai.vo.PublicUserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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

    @CrossOrigin
    @ApiOperation("获取个人的公开展示信息")
    @GetMapping("/public/getPublicUserVo/{userId}")
    @ResponseBody
    public PublicUserVo getPublicUserVo(@PathVariable Integer userId){
        return userService.getPublicUserVo(userId);
    }
}
