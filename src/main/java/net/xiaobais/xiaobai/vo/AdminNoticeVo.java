package net.xiaobais.xiaobai.vo;

import lombok.Data;

/**
 * @Author xiaobai
 * @Date 2021/5/10 11:55
 * @Version 1.0
 */
@Data
public class AdminNoticeVo {
    private Integer noticeId;
    private String username;
    private String acceptName;
    private Integer submitType;
    private boolean acceptType;
    private Integer nodeId;
    private Integer suggestId;
    private Integer iteratorId;
    private String message;
    private boolean isDelete;
}
