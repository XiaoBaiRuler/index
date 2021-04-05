package net.xiaobais.xiaobai.vo;

import lombok.Data;

/**
 * @Author xiaobai
 * @Date 2021/4/4 16:56
 * @Version 1.0
 */
@Data
public class AddNodeVo {
    private String blogTitle;
    private String content;
    private String desc;
    private String mapData;
    private Integer userId;
    private String relationShip;
}
