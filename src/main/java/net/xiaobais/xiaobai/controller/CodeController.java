package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ApiOperation("获取验证码")
    @GetMapping("/generateCode")
    @ResponseBody
    public String getCode(@RequestParam String email){
        return codeService.generateCode(email);
    }

    @ApiOperation("判断验证码是否正确")
    @GetMapping("/verifyCode")
    public String updatePassword(@RequestParam String email, @RequestParam String code){
        if (codeService.verifyCode(email, code)){
            return "redirect:/index";
        }
        return "verifyEmail";
    }
}
