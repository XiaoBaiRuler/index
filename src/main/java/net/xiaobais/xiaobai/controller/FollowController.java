package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.service.FansService;
import net.xiaobais.xiaobai.service.FollowService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author xiaobai
 * @Date 2021/4/12 18:27
 * @Version 1.0
 */
@Api(tags = "FollowController")
@Controller
public class FollowController {

    @Resource
    private FollowService followService;
    @Resource
    private FansService fansService;

    @ApiOperation("判断是否关注了")
    @GetMapping("/public/isFollow")
    @ResponseBody
    public Integer isFollow(@RequestParam Integer userId, @RequestParam Integer followId){
        return followService.getFollowByUserIdAndFollowId(userId, followId) ? 1 : 0;
    }


    @ApiOperation("关注用户")
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/public/follow")
    @ResponseBody
    public void addFollowAndFans(@RequestParam Integer userId, @RequestParam Integer followId)
            throws Exception {
        if (userId.equals(followId)){
            return;
        }
        if (followService.getFollowByUserIdAndFollowId(userId, followId)){
            throw new Exception("已存在关系");
        }
        synchronized (this){
            int i = followService.addFollowByUserIdAndFollowId(userId, followId);
            if (i == -1){
                throw new Exception("关注关系添加失败");
            }
            int j = fansService.addFansByUserIdAndFansId(followId, userId);
            if (j == -1){
                throw new Exception("粉丝关系添加失败");
            }
        }
    }

    @ApiOperation("取消关注用户")
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/public/unFollow")
    @ResponseBody
    public Integer deleteFollowAndFans(@RequestParam Integer userId, @RequestParam Integer followId)
            throws Exception {
        if (!followService.getFollowByUserIdAndFollowId(userId, followId)){
            return 0;
        }
        synchronized (this){
            if (followService.deleteFollowByUserIdAndFollowId(userId, followId) == -1){
                throw new Exception("关注关系删除失败");
            }
            if (fansService.deleteFansByUserIdAndfansId(followId, userId) == -1){
                throw new Exception("粉丝关系删除失败");
            }
        }
        return 1;
    }
}
