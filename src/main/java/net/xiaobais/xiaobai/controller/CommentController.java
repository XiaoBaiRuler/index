package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.service.CommentService;
import net.xiaobais.xiaobai.vo.AddCommentVo;
import net.xiaobais.xiaobai.vo.CommentVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/4/7 10:50
 * @Version 1.0
 */
@Api(tags = "CommentController")
@Controller
public class CommentController {

    @Resource
    private CommentService commentService;

    @ApiOperation("根据节点ID获取评论列表")
    @GetMapping("/public/getAllComments")
    @ResponseBody
    public List<CommentVo> getAllComments(@RequestParam Integer nodeId){
        return commentService.getAllComments(nodeId);
    }

    @ApiOperation("添加评论")
    @PostMapping("/person/public/addComment")
    @ResponseBody
    public Integer addComment(AddCommentVo commentVo){
        return commentService.addComment(commentVo);
    }
}
