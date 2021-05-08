package net.xiaobais.xiaobai.utils;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

/**
 * @Author xiaobai
 * @Date 2021/4/24 21:45
 * @Version 1.0
 */
public class CompressPictureUtils {
    public static void main(String[] args) throws IOException {
        Thumbnails.of(new File("E:\\ALL_Resource\\Idea\\index\\src\\main\\resources\\static\\img\\bh.png"))
                .size(480, 480)
                .toFile(new File("E:\\ALL_Resource\\Idea\\index\\src\\main\\resources\\static\\img\\bh_480.png"));
    }
}
