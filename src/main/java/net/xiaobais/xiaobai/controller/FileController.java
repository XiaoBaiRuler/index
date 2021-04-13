package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.service.FileService;
import net.xiaobais.xiaobai.service.UserService;
import net.xiaobais.xiaobai.utils.JwtUtils;
import net.xiaobais.xiaobai.vo.DirVo;
import net.xiaobais.xiaobai.vo.FileVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/4/13 10:41
 * @Version 1.0
 */
@Api(tags = "FileController")
@Controller
public class FileController {

    @Resource
    private FileService fileService;
    @Resource
    private UserService userService;

    @Value("${file.dir}")
    private String directory;

    private static final String NAME = "xiaobai_img";

    private static final String URL = "http://www.xiaobais.net:8080/image/";

    @ApiOperation("单文件上传")
    @PostMapping("/public/file/uploadOneFile")
    @ResponseBody
    public String uploadOneFile(MultipartFile file, String key,  String userDir, String username){
        // 认证层
        if (!DigestUtils.md5Hex(username + NAME).equals(key)){
            return "用户不存在";
        }
        // 合法层
        if (file.isEmpty()){
            return "错误:{文件为空，不能上传}";
        }
        if (file.getSize() <= 0){
            return "错误:{上传文件不能小于0KB}";
        }
        String newFilePath = directory + key;
        if (!"".equals(userDir)){
            newFilePath = newFilePath + "//" + userDir;
        }
        File newFile = new File(newFilePath);
        if (!newFile.exists()){
            return "错误:{" + userDir + "目录不存在}";
        }
        // 业务层
        String originFileName = file.getOriginalFilename();
        String str = newFilePath + "//" + originFileName;
        return fileService.uploadFile(str, file) ? originFileName : "错误:{上传失败}";
    }

    @ApiOperation("多文件上传")
    @PostMapping("/public/file/uploadMoreFile")
    @ResponseBody
    public String uploadMoreFile(MultipartFile[] files, String key, String username, String userDir){
        // 认证层
        if (!DigestUtils.md5Hex(username + NAME).equals(key)){
            return "用户不存在";
        }
        // 合法层
        if (files == null || files.length == 0){
            return "文件为空，不能上传";
        }
        String newFilePath = directory + key;
        if (!"".equals(userDir)){
            newFilePath = newFilePath + "//" + userDir;
        }
        File newFile = new File(newFilePath);
        if (!newFile.exists()){
            return "错误:{" + userDir + "目录不存在}";
        }
        // 业务层
        for (MultipartFile file : files) {
            String origin = file.getOriginalFilename();
            String str = newFilePath + "//" + origin;
            if (!fileService.uploadFile(origin, file)){
                return origin + "上传失败";
            }
        }
        return "上传成功";
    }

    @ApiOperation("创建文件夹")
    @PostMapping("/public/file/createMyDir")
    @ResponseBody
    public String createMyDir(String myDir, String username, String key){
        // 认证层
        if (!DigestUtils.md5Hex(username + NAME).equals(key)){
            return "用户不存在";
        }
        // 合法层
        String newFilePath = directory + key;
        String[] strs = myDir.split("/");
        if (!"".equals(strs[0])){
            newFilePath = newFilePath + "//" + strs[0];
        }
        File newFile = new File(newFilePath);
        if (newFile.exists()){
            return strs[0] + "目录已经存在";
        }
        else {
            return newFile.mkdirs() ? "添加" + strs[0] + "成功" : "添加" + strs[0] + "失败";
        }
    }

    @ApiOperation("删除文件或文件夹")
    @GetMapping("/public/file/deleteFile")
    @ResponseBody
    public String deleteFile(String path, String username, String key){
        // 认证层
        if (!DigestUtils.md5Hex(username + NAME).equals(key)){
            return "用户不存在";
        }
        // 合法层
        if ("".equals(path)){
            return "根目录不给删除";
        }
        File file = new File(directory + key + "//" + path);
        if (file.isDirectory()){
            File[] files = file.listFiles();
            if (files == null){
                return "删除文件夹失败";
            }
            if (files.length == 0){
                return file.delete() ? "删除文件夹成功" : "删除文件夹失败";
            }
        }
        else{
            return file.delete() ? "删除文件成功" : "删除文件失败";
        }
        return "删除失败";
    }

    @ApiOperation("修改文件目录名")
    @PostMapping("/public/file/updateDirName")
    @ResponseBody
    public String updateDirectoryName(String newDir, String oldDir, String username, String key){

        // 认证层
        if (!DigestUtils.md5Hex(username + NAME).equals(key)){
            return "用户不存在";
        }
        // 源路径
        File file = new File(directory + key + "//" + oldDir);
        File newFile = new File(directory + key + "//" + newDir);
        return file.renameTo(newFile) ? "修改成功" : "修改失败";
    }

    @ApiOperation("修改文件名")
    @PostMapping("/public/file/updateFileName")
    @ResponseBody
    public String updateFileName(String newName, String oldName, String userDir,
                                 String username, String key){
        // 认证层
        if (!DigestUtils.md5Hex(username + NAME).equals(key)){
            return "用户不存在";
        }
        // 源路径
        String str = directory + key;
        if (!"".equals(userDir)){
            str = str + "//" + userDir;
        }
        File dir = new File(str);
        if (!dir.exists()){
            return "目录里不存在";
        }
        File file = new File(str + "//" + oldName);
        File newFile = new File(str + "//" + oldName);
        return file.renameTo(newFile) ? "修改成功" : "修改失败";
    }

    @ApiOperation("读取所有目录")
    @GetMapping("/public/file/getAllDir")
    @ResponseBody
    public List<DirVo> getAllDir(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        String username = JwtUtils.getUsername(cookies[0].getValue());
        String key = DigestUtils.md5Hex(username + NAME);
        File file = new File(directory + key);
        File[] files = file.listFiles();
        if (files == null){
            return null;
        }
        List<DirVo> dirVos = new ArrayList<>();
        for (File value : files) {
            if (value.isDirectory()){
                DirVo dirVo = new DirVo();
                dirVo.setDir(value.getName());
                dirVo.setUsername(username);
                dirVo.setKey(key);
                dirVos.add(dirVo);
            }
        }
        return dirVos;
    }

    @ApiOperation("读取所有文件")
    @GetMapping("/public/file/getAllFile")
    @ResponseBody
    public List<FileVo> getAllFiles(HttpServletRequest request, @RequestParam String dir){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        String username = JwtUtils.getUsername(cookies[0].getValue());
        String key = DigestUtils.md5Hex(username + NAME);
        File file = new File(directory + key + "//" + dir);
        File[] files = file.listFiles();
        if (files == null){
            return null;
        }
        List<FileVo> fileVos = new ArrayList<>();
        for (File value : files){
            if (value.isFile()){
                FileVo fileVo = new FileVo();
                fileVo.setUsername(username);
                fileVo.setFileName(value.getName());
                fileVo.setKey(key);
                fileVo.setFileUrl(URL + key + "/" + dir + "/" + value.getName());
                fileVos.add(fileVo);
            }
        }
        return fileVos;
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("colatis" + NAME));
    }
}
