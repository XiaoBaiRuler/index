package net.xiaobais.xiaobai.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.service.UserService;
import net.xiaobais.xiaobai.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


/**
 * @Author xiaobai
 * @Date 2021/2/19 21:04
 * @Version 1.0
 */
@Api(tags = "LoginController")
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @ApiOperation("跳转登录页面")
    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @ApiOperation("检测密码是否正确")
    @PostMapping("/isWrong")
    @ResponseBody
    public String checkPassword(@RequestBody String data) throws UnsupportedEncodingException {
        String s = deleteData(data);
        UserVo userVo = JSONObject.parseObject(s, UserVo.class);
        return userService.checkPassword(userVo.getUsername(),
                userVo.getPassword());
    }

    private static String deleteData(String data) throws UnsupportedEncodingException {
        String s = URLDecoder.decode(data,"UTF-8");
        int i = 0;
        for (; i < s.length(); i++) {
            if (s.charAt(i) == '{'){
                break;
            }
        }
        int j = s.length() - 1;
        for (; j >= 0 ; j--) {
            if (s.charAt(j) == '}'){
                break;
            }
        }
        return s.substring(i, j + 1);
    }

}
