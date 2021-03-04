package net.xiaobais.xiaobai.vo;

import lombok.Data;

/**
 * @Author xiaobai
 * @Date 2021/3/3 17:02
 * @Version 1.0
 */
@Data
public class NodeVo {
    private Integer id;
    private String url;
    private String title;
    private String relationship;
    private String time;
    private Integer like;
    private Integer collect;
}
