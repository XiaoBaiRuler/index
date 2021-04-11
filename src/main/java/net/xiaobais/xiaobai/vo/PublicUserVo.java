package net.xiaobais.xiaobai.vo;

import lombok.Data;

/**
 * @Author xiaobai
 * @Date 2021/4/11 17:44
 * @Version 1.0
 */
@Data
public class PublicUserVo {

    private String username;
    private String email;
    private String userDesc;
    private String signTime;
    private Integer follows;
    private Integer fans;
    private Integer publicBlogs;
    private Integer collectBlogs;
    private Integer likeBlogs;
}
