package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.User;
import net.xiaobais.xiaobai.service.CodeService;
import net.xiaobais.xiaobai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author xiaobai
 * @Date 2021/2/22 14:13
 * @Version 1.0
 */
@Api(tags = "CodeController")
@Controller
public class CodeController {
    @Autowired
    private CodeService codeService;
    @Resource
    private UserService userService;

    @CrossOrigin
    @ApiOperation("获取验证码")
    @GetMapping("/user/generateCode")
    @ResponseBody
    public String getCode(@RequestParam String email){
        return codeService.generateCode(email);
    }

    @ApiOperation("判断验证码是否正确")
    @PostMapping("/user/verifyCode")
    @ResponseBody
    public String updatePassword(String email, String code, Integer userId){
        if (codeService.verifyCode(email, code)){
            User user = new User();
            user.setUserId(userId);
            user.setIsAuth(true);
            return userService.updateUser(user) != -1 ? "验证成功" : "验证失败";
        }
        return "验证失败";
    }
}
