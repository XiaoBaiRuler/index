package net.xiaobais.xiaobai.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author xiaobai
 * @Date 2021/4/13 11:15
 * @Version 1.0
 */
public interface FileService {

    /**
     * 上传一个文件
     * @param path path
     * @param file file
     * @return boolean
     */
    boolean uploadFile(String path, MultipartFile file);

    /**
     * 通过用户名创建用户图片的根路径
     * @param username username
     * @return boolean
     */
    boolean createRootDirectory(String username);

}
