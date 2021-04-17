package net.xiaobais.xiaobai.vo;

import lombok.Data;

/**
 * @Author xiaobai
 * @Date 2021/4/17 19:23
 * @Version 1.0
 */
@Data
public class IteratorNoticeVo {
    private Integer noticeId;
    private boolean isReply;
    private String message;
    private Integer iteratorId;
    private Integer nodeId;
}
