package net.xiaobais.xiaobai.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.service.SuggestService;
import net.xiaobais.xiaobai.vo.AdminSuggestVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/5/10 13:57
 * @Version 1.0
 */
@Api(tags = "AdminSuggestController")
@Controller
@RequestMapping("/admin")
public class AdminSuggestController {

    @Resource
    private SuggestService suggestService;

    @ApiOperation("跳转建议管理主页")
    @GetMapping("/toAdminSuggest")
    public String toAdminSuggest(){
        return "admin/adminSuggest";
    }

    @ApiOperation("根据信息分页查找所有通知信息")
    @GetMapping("/getAllSuggest/{type}")
    @ResponseBody
    public List<AdminSuggestVo> getAllSuggest(@PathVariable Integer type,
                                              @RequestParam Integer pageNumber,
                                              @RequestParam Integer pageSize,
                                             @RequestParam String message){
        return suggestService.getAllSuggest(type,pageNumber, pageSize, message);
    }

    @ApiOperation("根据信息统计建议个数")
    @GetMapping("/countAllSuggest/{type}")
    @ResponseBody
    public Long countAllSuggest(@PathVariable Integer type,@RequestParam String message){
        return suggestService.countAllSuggest(type, message);
    }

    @ApiOperation("删除建议节点")
    @GetMapping("/deleteSuggest")
    @ResponseBody
    public String deleteSuggest(Integer suggestId){
        return suggestService.deleteSuggest(suggestId) ? "#" : "删除失败";
    }
}
