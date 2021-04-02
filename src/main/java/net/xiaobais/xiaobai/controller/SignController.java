package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Blog;
import net.xiaobais.xiaobai.model.Map;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author xiaobai
 * @Date 2021/2/22 13:31
 * @Version 1.0
 */
@Api(tags = "SignController")
@Controller
public class SignController {

    @Autowired
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private PublicNodeService nodeService;
    @Resource
    private BlogService blogService;
    @Resource
    private MapService mapService;

    @ApiOperation("跳转注册页面")
    @GetMapping("/toSign")
    public String toSign(){
        return "sign";
    }

    @ApiOperation("注册处理")
    @PostMapping("/sign")
    public String sign(String username, String email, String password){
        synchronized (this){
            //1. 添加用户
            int userId = userService.addUser(username, email, password);
            //2. 添加用户个人节点
            Integer nodeId = addNode(userId, username);
            userService.insertIndexId(nodeId, userId);
            //3. 添加普通用户权限
            int roleId = roleService.getRoleIdByRoleName("user");
            userRoleService.addUserRole(userId, roleId);
        }
        return "redirect:/index";
    }

    @CrossOrigin
    @ApiOperation("是否已存在用户名")
    @GetMapping("/user/hasUser")
    @ResponseBody
    public String hasUser(@RequestParam String username){
        return userService.hasUser(username) ? "1" : "0";
    }

    private Integer addNode(Integer userId, String username){
        Blog blog = new Blog();
        blog.setBlogTitle(username);
        blog.setBlogContent("# 欢迎" + username);
        int blogId = blogService.insertBlog(blog);
        Map map = new Map();
        map.setMapName(username);
        map.setMapAuthor(username);
        map.setMapVersion("1.0");
        map.setMapData("[]");
        int mapId = mapService.insertMap(map);
        Node node = new Node();
        node.setNodeName(username);
        node.setUserId(userId);
        node.setBlogId(blogId);
        node.setMapId(mapId);
        node.setCollect(0);
        node.setIsPrivate(true);
        node.setStar(0);
        node.setCreateDate(new Date());
        node.setUpdateDate(new Date());
        node.setRelationship(username);
        return nodeService.insertNode(node);
    }
}
