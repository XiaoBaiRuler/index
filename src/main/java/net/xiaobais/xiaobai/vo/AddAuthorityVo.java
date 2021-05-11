package net.xiaobais.xiaobai.vo;

import lombok.Data;

/**
 * @Author xiaobai
 * @Date 2021/5/11 18:10
 * @Version 1.0
 */
@Data
public class AddAuthorityVo {
    private Integer roleId;
    private String authorityUrl;
    private String authorityName;
    private String authorityTag;
}
