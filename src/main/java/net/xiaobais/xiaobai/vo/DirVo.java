package net.xiaobais.xiaobai.vo;

import lombok.Data;

/**
 * @Author xiaobai
 * @Date 2021/4/13 16:04
 * @Version 1.0
 */
@Data
public class DirVo {
    private String dir;

    public DirVo() {
    }

    public DirVo(String dir) {
        this.dir = dir;
    }
}
