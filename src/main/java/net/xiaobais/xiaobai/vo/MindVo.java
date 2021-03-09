package net.xiaobais.xiaobai.vo;

import lombok.Data;

/**
 * @Author xiaobai
 * @Date 2021/3/7 19:29
 * @Version 1.0
 */
@Data
public class MindVo {
    private String id;
    private String parentid;
    private boolean isroot;
    private String topic;
    private String direction;
    private boolean expanded;

    public MindVo() {
    }

    public MindVo(String id, String parentid, boolean isroot, String topic, String direction, boolean expanded) {
        this.id = id;
        this.parentid = parentid;
        this.isroot = isroot;
        this.topic = topic;
        this.direction = direction;
        this.expanded = expanded;
    }
}
