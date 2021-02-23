package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.service.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

/**
 * @Author xiaobai
 * @Date 2021/2/22 17:42
 * @Version 1.0
 */
@Api(tags = "EmailController")
@Controller
public class EmailController {

    @Resource
    private EmailService emailService;

    @ApiOperation("跳转验证邮箱页面")
    @GetMapping("/toVerifyEmail")
    public String toVerifyEmail(){
        return "verifyEmail";
    }

    @ApiOperation("验证邮箱")
    @PostMapping("/verifyEmail")
    public void verifyEmail(String email){
        emailService.sendEmailVerifyCode(email);
    }
}
