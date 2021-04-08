package net.xiaobais.xiaobai.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/4/7 10:33
 * @Version 1.0
 */
@Data
public class CommentVo {
    private Integer commentId;
    private Integer userId;
    private Integer nodeId;
    private String avatar;
    private String userUrl;
    private String username;
    private String time;
    private String comment;
    private String userEmail;
    private boolean flag;
    private List<CommentVo> children;
}
