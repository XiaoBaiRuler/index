package net.xiaobais.xiaobai.vo;

import lombok.Data;

import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/4/9 17:10
 * @Version 1.0
 */
@Data
public class UpdatePrivateVo {

    private String title;
    private String desc;
    private String blogContent;
    private String mapData;
    private List<String> select;
}
