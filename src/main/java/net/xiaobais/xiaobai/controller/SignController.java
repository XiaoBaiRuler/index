package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Blog;
import net.xiaobais.xiaobai.model.Map;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    @Resource
    private FileService fileService;

    @ApiOperation("跳转注册页面")
    @GetMapping("/toSign")
    public String toSign(){
        return "sign";
    }

    @ApiOperation("注册处理")
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/sign")
    public String sign(String username, String email, String password) throws Exception {
        synchronized (this){
            //1. 添加用户
            int userId = userService.addUser(username, email, password);
            if (userId == -1){
                throw new Exception("用户添加失败");
            }
            //2. 添加用户个人节点
            Integer nodeId = addNode(userId, username);
            if (nodeId == -1){
                throw new Exception("用户根节点添加失败");
            }
            int i = userService.insertIndexId(nodeId, userId);
            if (i == -1){
                throw new Exception("添加根节点Id失败");
            }
            //3. 添加普通用户权限
            int roleId = roleService.getRoleIdByRoleName("user");
            if (roleId == -1){
                throw new Exception("添加权限失败");
            }
            int j = userRoleService.addUserRole(userId, roleId);
            if (j == -1){
                throw new Exception("绑定角色失败");
            }
            //4. 创建归属于用户的图片文件夹
            if (!fileService.createRootDirectory(username)){
                throw new Exception("创建图片文件夹失败");
            }
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
        blog.setCreateDate(new Date());
        blog.setUpdateDate(new Date());
        blog.setIsComment(false);
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
