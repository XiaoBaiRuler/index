package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author xiaobai
 * @Date 2021/2/19 21:04
 * @Version 1.0
 */
@Api(tags = "LoginController")
@Controller
public class LoginController {

    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

}
