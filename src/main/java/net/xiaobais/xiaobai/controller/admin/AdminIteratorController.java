package net.xiaobais.xiaobai.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Iterator;
import net.xiaobais.xiaobai.service.IteratorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/5/10 17:36
 * @Version 1.0
 */
@Api(tags = "AdminIteratorController")
@Controller
@RequestMapping("/admin")
public class AdminIteratorController {

    @Resource
    private IteratorService iteratorService;

    @ApiOperation("跳转迭代节点管理主页")
    @GetMapping("/toAdminIterator")
    public String addAuthority(){
        return "admin/adminIterator";
    }

    @ApiOperation("根据信息分页查找所有通知信息")
    @GetMapping("/getAllIterator")
    @ResponseBody
    public List<Iterator> getAllIterator(@RequestParam Integer pageNumber,
                                         @RequestParam Integer pageSize,
                                         @RequestParam String message){
        return iteratorService.getAllIterator(pageNumber, pageSize, message);
    }

    @ApiOperation("根据信息统计通知个数")
    @GetMapping("/countAllIterator")
    @ResponseBody
    public Long countAllIterator(@RequestParam String message){
        return iteratorService.countAllIterator(message);
    }

    @ApiOperation("删除迭代关系")
    @GetMapping("/deleteIterator")
    @ResponseBody
    public String deleteIterator(@RequestParam Integer nodeId, @RequestParam Integer iteratorId){
        try {
            iteratorService.deleteIterator(nodeId, iteratorId);
            return "#";
        } catch (Exception e) {
            e.printStackTrace();
            return "删除失败";
        }
    }

    @ApiOperation("更新迭代理由")
    @PostMapping("/updateIterator/{iteratorId}")
    @ResponseBody
    public String updateIterator(@PathVariable Integer iteratorId, Integer nodeId, String reason){
        return iteratorService.updateIterator(iteratorId, nodeId, reason) ? "#" : "更新失败";
    }
}
