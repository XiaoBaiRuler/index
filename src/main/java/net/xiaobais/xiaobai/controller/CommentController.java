package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.service.CacheService;
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
    @Resource
    private CacheService cacheService;

    private static final String COMMENT_CACHE = "/public/getAllComments";

    @ApiOperation("根据节点ID获取评论列表")
    @GetMapping("/public/getAllComments")
    @ResponseBody
    public List<CommentVo> getAllComments(@RequestParam Integer nodeId){
        List<CommentVo> comments = cacheService.getCommentsByKey(COMMENT_CACHE + nodeId);
        if (comments == null){
            comments = commentService.getAllComments(nodeId);
            cacheService.setCommentsByKey(COMMENT_CACHE + nodeId, comments);
        }
        return comments;
    }

    @ApiOperation("添加评论")
    @PostMapping("/person/public/addComment")
    @ResponseBody
    public Integer addComment(AddCommentVo commentVo){
        int i = commentService.addComment(commentVo);
        if (i != -1){
            cacheService.deleteCommentsByKey(COMMENT_CACHE + commentVo.getNodeId());
        }
        return i;
    }

    @ApiOperation("删除评论")
    @GetMapping("/admin/deleteComment/{commentId}")
    @ResponseBody
    public String deleteComment(@PathVariable Integer commentId){
        int nodeId = commentService.deleteCommentByCommentId(commentId);
        if (nodeId == -2){
            return "还有子评论";
        }
        if (nodeId != -1){
            cacheService.deleteCommentsByKey(COMMENT_CACHE + nodeId);
            return "#";
        }
        return "删除失败";
    }
}
