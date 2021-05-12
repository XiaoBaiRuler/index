package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.mapper.CommentMapper;
import net.xiaobais.xiaobai.model.Comment;
import net.xiaobais.xiaobai.model.CommentExample;
import net.xiaobais.xiaobai.model.User;
import net.xiaobais.xiaobai.service.CommentService;
import net.xiaobais.xiaobai.service.UserService;
import net.xiaobais.xiaobai.vo.AddCommentVo;
import net.xiaobais.xiaobai.vo.CommentVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/4/7 10:56
 * @Version 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private UserService userService;

    @Override
    public List<Comment> getAllCommentsByNodeId(Integer nodeId) {
        CommentExample example = new CommentExample();
        example.createCriteria()
                .andNodeIdEqualTo(nodeId)
                .andIsDeleteEqualTo(false)
                .andParentIdEqualTo(0);
        example.setOrderByClause("update_date desc");
        return commentMapper.selectByExample(example);
    }

    @Override
    public List<CommentVo> getAllComments(Integer nodeId) {
        List<Comment> comments = getAllCommentsByNodeId(nodeId);
        List<CommentVo> commentVos = new ArrayList<>();
        comments.forEach(c -> {
            List<CommentVo> next = getAllCommentsByParentIdAndNodeId(c.getCommentId(), c.getNodeId());
            commentVos.add(commentToCommentVo(c, !next.isEmpty(), next));
        });
        return commentVos;
    }

    @Override
    public List<CommentVo> getAllCommentsByParentIdAndNodeId(Integer parentId, Integer nodeId) {
        CommentExample example = new CommentExample();
        example.createCriteria()
                .andNodeIdEqualTo(nodeId)
                .andIsDeleteEqualTo(false)
                .andParentIdEqualTo(parentId);
        example.setOrderByClause("update_date desc");
        List<Comment> comments = commentMapper.selectByExample(example);
        List<CommentVo> commentVos = new ArrayList<>();
        comments.forEach(c -> {
            List<CommentVo> next = getAllCommentsByParentIdAndNodeId(c.getCommentId(), c.getNodeId());
            commentVos.add(commentToCommentVo(c, !next.isEmpty(), next));
        });
        return commentVos;
    }

    @Override
    public int addComment(AddCommentVo commentVo) {
        Comment comment = new Comment();
        comment.setUserId(commentVo.getUserId());
        comment.setContent(commentVo.getContent());
        comment.setNodeId(commentVo.getNodeId());
        comment.setParentId(commentVo.getParentId());
        comment.setCreateDate(new Date());
        comment.setUpdateDate(new Date());
        comment.setIsDelete(false);
        commentMapper.insert(comment);
        return comment.getCommentId();
    }

    @Override
    public int deleteCommentByNodeId(Integer nodeId) {
        CommentExample example = new CommentExample();
        example.createCriteria().andNodeIdEqualTo(nodeId);
        return commentMapper.deleteByExample(example);
    }

    @Override
    public int deleteCommentByCommentId(Integer commentId) {
        Comment comment = commentMapper.selectByPrimaryKey(commentId);
        if (comment == null){
            return -2;
        }
        CommentExample example = new CommentExample();
        example.createCriteria().andParentIdEqualTo(commentId)
                .andNodeIdEqualTo(comment.getNodeId());
        List<Comment> comments = commentMapper.selectByExample(example);
        if (comments != null && !comments.isEmpty()){
            return -2;
        }
        return commentMapper.deleteByPrimaryKey(commentId) != -1 ? comment.getNodeId() : -1;
    }

    private CommentVo commentToCommentVo(Comment comment, boolean f, List<CommentVo> children){
        CommentVo commentVo = new CommentVo();
        User user = userService.getUserById(comment.getUserId());
        commentVo.setCommentId(comment.getCommentId());
        commentVo.setUserId(comment.getUserId());
        commentVo.setNodeId(comment.getNodeId());
        commentVo.setAvatar(user.getUserAvatar());
        commentVo.setUserUrl("/public/user/" + comment.getUserId());
        commentVo.setUsername(user.getUsername());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Period p = Period.between(
                LocalDate.parse(df.format(comment.getUpdateDate())), LocalDate.now());
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        if (p.getYears() != 0) {
            flag = true;
            sb.append(p.getYears()).append("年");
        }
        if (p.getMonths() != 0){
            flag = true;
            sb.append(p.getMonths()).append("月");
        }
        if (p.getDays() != 0){
            flag = true;
            sb.append(p.getDays()).append("日");
        }
        if (!flag){
            sb.append("今天");
        }
        else{
            sb.append("前");
        }
        commentVo.setTime(sb.toString());
        commentVo.setComment(comment.getContent());
        commentVo.setUserEmail(user.getUserEmail());
        commentVo.setFlag(f);
        commentVo.setChildren(children);
        return commentVo;
    }
}
