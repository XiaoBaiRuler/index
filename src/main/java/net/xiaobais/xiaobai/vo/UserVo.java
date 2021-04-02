package net.xiaobais.xiaobai.vo;

import lombok.Data;

/**
 * @Author xiaobai
 * @Date 2021/2/26 11:48
 * @Version 1.0
 */
@Data
public class UserVo {
    private String password;
    private String username;
    private String url;
    private String img;
    private Integer userId;
    private Integer indexId;
}
