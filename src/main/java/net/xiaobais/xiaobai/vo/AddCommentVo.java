package net.xiaobais.xiaobai.vo;

import lombok.Data;

/**
 * @Author xiaobai
 * @Date 2021/4/8 12:20
 * @Version 1.0
 */
@Data
public class AddCommentVo {

    private Integer parentId;
    private Integer userId;
    private Integer nodeId;
    private String content;
}