package net.xiaobais.xiaobai.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.User;
import net.xiaobais.xiaobai.service.CacheService;
import net.xiaobais.xiaobai.service.FileService;
import net.xiaobais.xiaobai.service.UserService;
import net.xiaobais.xiaobai.vo.EditUserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/4/22 10:02
 * @Version 1.0
 */
@Api(tags = "后台用户管理")
@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Resource
    private UserService userService;
    @Resource
    private FileService fileService;
    @Resource
    private CacheService cacheService;

    private static final String NAME = "xiaobai_img";
    private static final String PREF_KEY = "/admin/getAllUser";

    @ApiOperation("跳转用户管理主页")
    @GetMapping("/toAdminUser")
    public String toAdminUser(){
        return "admin/adminUser";
    }

    @ApiOperation("分页查找所有用户信息")
    @GetMapping("/getAllUser")
    @ResponseBody
    public List<User> getAllUser(@RequestParam Integer pageNumber,
                                 @RequestParam Integer pageSize,
                                 @RequestParam String message){
        String key = PREF_KEY + "#" + pageNumber + "#" + pageSize + "#" + message;
        List<User> list = cacheService.getUserList(key);
        if (list == null){
            list = userService.getAllUsersByPageAndMessage(pageNumber, pageSize, message);
            cacheService.setUserList(key, list);
        }
        return list;
    }

    @ApiOperation("根据用户名模糊查询用户个数")
    @GetMapping("/countAllUser")
    @ResponseBody
    public Long countAllUser(@RequestParam String message){
        return userService.countAllUsersByMessage(message);
    }

    @ApiOperation("禁用用户")
    @GetMapping("/stopUser")
    @ResponseBody
    public String stopUser(Integer userId){
        return userService.dealUser(false, userId) ? "禁用成功" : "#禁用失败";
    }

    @ApiOperation("解禁用户")
    @GetMapping("/agreeUser")
    @ResponseBody
    public String agreeUser(Integer userId){
        return userService.dealUser(true, userId) ? "解禁成功" : "#解禁失败";
    }

    @ApiOperation("更新用户部分信息")
    @PostMapping("/updateUser/{userId}")
    @ResponseBody
    public String editUser(@PathVariable Integer userId, EditUserVo editUserVo){
        if (editUserVo.getUsername().length() < 6){
            return "#需要6个字符";
        }
        User user = userService.getUserById(userId);
        String avatar = user.getUserAvatar();
        if (!user.getUsername().equals(editUserVo.getUsername())){
            if (userService.hasUser(editUserVo.getUsername())){
                return "#用户名已存在";
            }
            if(!fileService.updateRootName(editUserVo.getUsername(), user.getUsername())){
                return "#修改失败";
            }
            avatar = user.getUserAvatar().replace(DigestUtils.md5Hex(user.getUsername() + NAME),
                    DigestUtils.md5Hex(editUserVo.getUsername() + NAME));
        }
        if (userService.editUser(userId, editUserVo, avatar)){
            cacheService.deleteAllUserByPreKey(PREF_KEY);
            return "修改成功";
        }
        else{
            return "#修改失败";
        }
    }
}
