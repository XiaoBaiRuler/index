package net.xiaobais.xiaobai.vo;

import lombok.Data;

/**
 * @Author xiaobai
 * @Date 2021/4/16 19:20
 * @Version 1.0
 */
@Data
public class PublicNoticeVo {

    private Integer noticeId;
    private String username;
    private String type;
    private String message;
    private Integer nodeId;
    private String dealUrl;
    private String errorUrl;
    private Integer userId;
}
