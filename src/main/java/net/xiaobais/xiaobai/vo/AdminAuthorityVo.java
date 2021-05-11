package net.xiaobais.xiaobai.vo;

import lombok.Data;

/**
 * @Author xiaobai
 * @Date 2021/5/11 9:16
 * @Version 1.0
 */
@Data
public class AdminAuthorityVo {
    private Integer roleId;
    private Integer authorityId;
    private String authorityUrl;
    private String authorityName;
    private String authorityTag;
}
