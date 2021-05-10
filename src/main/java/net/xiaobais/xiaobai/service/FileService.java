package net.xiaobais.xiaobai.service;

import net.xiaobais.xiaobai.vo.DirVo;
import net.xiaobais.xiaobai.vo.FileVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

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

    /**
     * 根据s获取所有目录
     * @param files files
     * @param s s
     * @param pageNumber pageNumber
     * @return List<DirVo>
     */
    List<DirVo> getAllDir(File[] files, String s, Integer pageNumber);

    /**
     * 根据s模糊统计目录个数
    * @param files files
     * @param s s
     * @return Integer
     */
    Integer getCountDir(File[] files, String s);

    /**
     * 根据s读取所有文件
     * @param files files
     * @param s s
     * @param pageNumber pageNumber
     * @param prePath prePath
     * @return List<FileVo>
     */
    List<FileVo> getAllFiles(File[] files, String s, Integer pageNumber, String prePath);

    /**
     * 根据s统计文件个数
     * @param files files
     * @param s s
     * @return Integer
     */
    Integer getCountFiles(File[] files, String s);

    /**
     * 更新用户名而更新的根目录
     * @param username username
     * @param oldName oldName
     * @return boolean
     */
    boolean updateRootName(String username, String oldName);

}
