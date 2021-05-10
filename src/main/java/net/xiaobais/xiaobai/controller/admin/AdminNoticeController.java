package net.xiaobais.xiaobai.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.service.NoticeService;
import net.xiaobais.xiaobai.vo.AdminNoticeVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/5/10 11:53
 * @Version 1.0
 */
@Api(tags = "AdminNoticeController")
@Controller
@RequestMapping("/admin")
public class AdminNoticeController {

    @Resource
    private NoticeService noticeService;

    @ApiOperation("跳转通知管理主页")
    @GetMapping("/toAdminNotice")
    public String toAdminUser(){
        return "admin/adminNotice";
    }

    @ApiOperation("根据信息分页查找所有通知信息")
    @GetMapping("/getAllNotice/{type}")
    @ResponseBody
    public List<AdminNoticeVo> getAllNotice(@PathVariable Integer type,
                                          @RequestParam Integer pageNumber,
                                          @RequestParam Integer pageSize,
                                          @RequestParam String message){
        return noticeService.getAllNotice(type, pageNumber, pageSize, message);
    }

    @ApiOperation("根据信息统计通知个数")
    @GetMapping("/countAllNotice/{type}")
    @ResponseBody
    public Long countAllNotice(@PathVariable Integer type,@RequestParam String message){
        return noticeService.countAllNotice(type, message);
    }

    @ApiOperation("管理员普通删除通知")
    @GetMapping("/deleteNotice/{noticeId}")
    @ResponseBody
    public String deleteNotice(@PathVariable Integer noticeId){
        return noticeService.deleteNotice(noticeId) ? "#" : "普通删除失败";
    }

    @ApiOperation("管理员彻底删除通知")
    @GetMapping("/completeDeleteNotice/{noticeId}")
    @ResponseBody
    public String completeDeleteNotice(@PathVariable Integer noticeId){
        return noticeService.completeDeleteNotice(noticeId) ? "#" : "彻底删除失败";
    }

    @ApiOperation("管理员更新通知信息")
    @PostMapping("/updateNoticeMessage/{noticeId}")
    @ResponseBody
    public String updateNoticeMessage(@PathVariable Integer noticeId, String message){
        return noticeService.updateNoticeMessage(noticeId, message) ? "#" : "更新失败";
    }
}
