package net.xiaobais.xiaobai.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Authority;
import net.xiaobais.xiaobai.model.Role;
import net.xiaobais.xiaobai.service.RoleAuthorityService;
import net.xiaobais.xiaobai.service.RoleService;
import net.xiaobais.xiaobai.vo.AddAuthorityVo;
import net.xiaobais.xiaobai.vo.AdminAuthorityVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/2/20 12:16
 * @Version 1.0
 */
@Api(tags = "AdminAuthorityController")
@Controller
@RequestMapping("/admin")
public class AdminAuthorityController {

    @Resource
    private RoleService roleService;
    @Resource
    private RoleAuthorityService roleAuthorityService;

    @ApiOperation("跳转权限管理页面")
    @GetMapping("/toAdminAuthority")
    public String addAuthority(){
        return "admin/adminAuthority";
    }

    @ApiOperation("获取所有角色")
    @GetMapping("/getAllRoles")
    @ResponseBody
    public List<Role> getAllRoles(){
        return roleService.getAll();
    }

    @ApiOperation("分页获取所有权限信息")
    @GetMapping("/getAllAuthorities/{roleId}")
    @ResponseBody
    public List<AdminAuthorityVo> getAllAuthorities(@PathVariable Integer roleId,
                                                    @RequestParam Integer pageNumber,
                                                    @RequestParam Integer pageSize,
                                                    @RequestParam String message){
        return roleAuthorityService.getAllAuthorities(roleId, pageNumber, pageSize, message);
    }

    @ApiOperation("统计权限个数")
    @GetMapping("/countAllAuthorities/{roleId}")
    @ResponseBody
    public Integer countAllAuthorities(@PathVariable Integer roleId,
                                       @RequestParam String message){
        return roleAuthorityService.countAllAuthorities(roleId, message);
    }

    @ApiOperation("给角色添加权限")
    @PostMapping("/addAuthority")
    @ResponseBody
    public String addAuthority(AddAuthorityVo addAuthorityVo){
        try {
            int i = roleAuthorityService.addRoleAuthority(addAuthorityVo);
            return i == -1 ? "添加权限失败" : "#"+i;
        } catch (Exception e) {
            return "添加权限失败";
        }
    }

    @ApiOperation("更新权限信息")
    @PostMapping("/updateAuthority")
    @ResponseBody
    public String updateAuthority(Authority authority){
        return roleAuthorityService.updateAuthority(authority) != -1 ? "#" : "更新权限失败";
    }

    @ApiOperation("删除权限信息")
    @GetMapping("/deleteAuthority")
    @ResponseBody
    public String deleteAuthority(@RequestParam Integer roleId,
                                  @RequestParam Integer authorityId){
        try {
            roleAuthorityService.deleteRoleAuthority(roleId, authorityId);
            return "#";
        } catch (Exception e) {
            e.printStackTrace();
            return "删除失败";
        }
    }

}
