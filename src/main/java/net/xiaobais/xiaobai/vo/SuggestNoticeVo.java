package net.xiaobais.xiaobai.vo;

import lombok.Data;

/**
 * @Author xiaobai
 * @Date 2021/4/17 23:11
 * @Version 1.0
 */
@Data
public class SuggestNoticeVo {

    private Integer noticeId;
    private Integer nodeId;
    private Integer suggestId;
    private String message;
    private boolean isReply;
}
