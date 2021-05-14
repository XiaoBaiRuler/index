package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.service.FileService;
import net.xiaobais.xiaobai.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * @Author xiaobai
 * @Date 2021/4/13 10:41
 * @Version 1.0
 */
@Api(tags = "FileController")
@Controller
@RequestMapping("/public")
public class PublicFileController {

    @Resource
    private FileService fileService;
    @Resource
    private UserService userService;

    @Value("${file.dir}")
    private String directory;

    private static final String NAME = "xiaobai_img";

    private static final String URL = "http://www.xiaobais.net:8080/image/";

    @CrossOrigin
    @ApiOperation("单文件上传")
    @PostMapping("/file/uploadOneFile")
    @ResponseBody
    public String uploadOneFile(MultipartFile file, String key,  String userDir, String username){
        System.out.println(username);
        System.out.println(key);
        System.out.println(userDir);
        // 认证层
        if (!DigestUtils.md5Hex(username + NAME).equals(key)){
            return "#用户不存在";
        }
        // 合法层
        if (file == null || file.isEmpty()){
            return "#文件为空，不能上传";
        }
        if (file.getSize() <= 0){
            return "#上传文件不能小于0KB";
        }
        String newFilePath = directory + key;
        if (!"".equals(userDir)){
            newFilePath = newFilePath + "//" + userDir;
        }
        File newFile = new File(newFilePath);
        if (!newFile.exists()){
            return "#" + userDir + "目录不存在";
        }
        // 业务层
        String originFileName = file.getOriginalFilename();
        String str = newFilePath + "//" + originFileName;
        return fileService.uploadFile(str, file) ? originFileName : "#上传失败";
    }

    @CrossOrigin
    @ApiOperation("多文件上传")
    @PostMapping("/file/uploadMoreFile")
    @ResponseBody
    public String uploadMoreFile(MultipartFile[] files, String key, String username, String userDir){
        // 认证层
        if (!DigestUtils.md5Hex(username + NAME).equals(key)){
            return "#用户不存在";
        }
        // 合法层
        if (files == null || files.length == 0){
            return "#文件为空，不能上传";
        }
        String newFilePath = directory + key;
        if (!"".equals(userDir)){
            newFilePath = newFilePath + "//" + userDir;
        }
        File newFile = new File(newFilePath);
        if (!newFile.exists()){
            return "#" + userDir + "目录不存在";
        }
        // 业务层
        for (MultipartFile file : files) {
            String origin = file.getOriginalFilename();
            String str = newFilePath + "//" + origin;
            if (!fileService.uploadFile(origin, file)){
                return origin + "#上传失败";
            }
        }
        return "上传成功";
    }

    @CrossOrigin
    @ApiOperation("创建文件夹")
    @PostMapping("/file/createMyDir")
    @ResponseBody
    public String createMyDir(String myDir, String username, String key){
        // 认证层
        if (!DigestUtils.md5Hex(username + NAME).equals(key)){
            return "#用户不存在";
        }
        // 合法层
        String newFilePath = directory + key;
        String[] strs = myDir.split("/");
        if (!"".equals(strs[0])){
            newFilePath = newFilePath + "//" + strs[0];
        }
        File newFile = new File(newFilePath);
        if (newFile.exists()){
            return strs[0] + "#目录已经存在";
        }
        else {
            return newFile.mkdirs() ? "添加" + strs[0] + "成功" : "#添加" + strs[0] + "失败";
        }
    }

    @CrossOrigin
    @ApiOperation("删除文件或文件夹")
    @GetMapping("/file/deleteFile")
    @ResponseBody
    public String deleteFile(String path, String username, String key){
        // 认证层
        if (!DigestUtils.md5Hex(username + NAME).equals(key)){
            return "#用户不存在";
        }
        // 合法层
        if ("".equals(path)){
            return "#根目录不给删除";
        }
        File file = new File(directory + key + "//" + path);
        if (file.isDirectory()){
            File[] files = file.listFiles();
            if (files == null){
                return "#删除文件夹失败";
            }
            if (files.length == 0){
                return file.delete() ? "删除文件夹成功" : "#删除文件夹失败";
            }
        }
        else{
            return file.delete() ? "删除文件成功" : "#删除文件失败";
        }
        return "#删除失败";
    }

    @CrossOrigin
    @ApiOperation("修改文件目录名")
    @PostMapping("/file/updateDirName")
    @ResponseBody
    public String updateDirectoryName(String newDir, String oldDir, String username, String key){

        // 认证层
        if (!DigestUtils.md5Hex(username + NAME).equals(key)){
            return "#用户不存在";
        }
        // 源路径
        File file = new File(directory + key + "//" + oldDir);
        File newFile = new File(directory + key + "//" + newDir);
        return file.renameTo(newFile) ? "修改成功" : "#修改失败";
    }

    @CrossOrigin
    @ApiOperation("修改文件名")
    @PostMapping("/file/updateFileName")
    @ResponseBody
    public String updateFileName(String newName, String oldName, String userDir,
                                 String username, String key){
        // 认证层
        if (!DigestUtils.md5Hex(username + NAME).equals(key)){
            return "#用户不存在";
        }
        // 源路径
        String str = directory + key;
        if (!"".equals(userDir)){
            str = str + "//" + userDir;
        }
        File dir = new File(str);
        if (!dir.exists()){
            return "#目录里不存在";
        }
        File file = new File(str + "//" + oldName);
        File newFile = new File(str + "//" + newName);
        return file.renameTo(newFile) ? "修改成功" : "#修改失败";
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("colatis" + NAME));
    }
}
