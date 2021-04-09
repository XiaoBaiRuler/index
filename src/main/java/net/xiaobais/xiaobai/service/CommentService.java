package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.model.Comment;
import net.xiaobais.xiaobai.vo.AddCommentVo;
import net.xiaobais.xiaobai.vo.CommentVo;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/4/7 10:53
 * @Version 1.0
 */
public interface CommentService {

    /**
     * 根据节点ID获取评论列表
     * @param nodeId nodeId
     * @return List<CommentVo>
     */
    List<Comment> getAllCommentsByNodeId(Integer nodeId);

    /**
     * 组合当前node的两层评论信息
     * @param nodeId nodeId
     * @return List<CommentVo>
     */
    List<CommentVo> getAllComments(Integer nodeId);

    /**
     * 根据parentId和nodeId获取下一层的评论信息
     * @param parentId parentId
     * @param nodeId nodeId
     * @return List<CommentVo>
     */
    List<CommentVo> getAllCommentsByParentIdAndNodeId(Integer parentId, Integer nodeId);

    /**
     * 添加评论
     * @param commentVo AddCommentVo
     * @return int
     */
    int addComment(AddCommentVo commentVo);

    /**
     * 根据nodeId删除评论
     * @param nodeId nodeId
     * @return int
     */
    int deleteCommentByNodeId(Integer nodeId);
}
