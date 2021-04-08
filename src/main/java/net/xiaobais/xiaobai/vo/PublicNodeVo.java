package net.xiaobais.xiaobai.vo;

import lombok.Data;

/**
 * @Author xiaobai
 * @Date 2021/4/6 17:37
 * @Version 1.0
 */
@Data
public class PublicNodeVo {

    private Integer id;
    private String userUrl;
    private String username;
    private String avatar;
    private String title;
    private String desc;
    private String time;
    private Integer like;
    private Integer collect;
    private String email;
}
