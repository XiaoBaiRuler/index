package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.coobird.thumbnailator.Thumbnails;
import net.xiaobais.xiaobai.model.User;
import net.xiaobais.xiaobai.service.FileService;
import net.xiaobais.xiaobai.service.UserService;
import net.xiaobais.xiaobai.utils.JwtUtils;
import net.xiaobais.xiaobai.vo.DirVo;
import net.xiaobais.xiaobai.vo.FileVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/4/14 10:10
 * @Version 1.0
 */
@Api(tags = "PersonFileController")
@Controller
@RequestMapping("/person/public")
public class PersonFileController {

    @Resource
    private UserService userService;
    @Resource
    private FileService fileService;

    @Value("${file.dir}")
    private String directory;
    @Value("${file.url}")
    private String url;
    private static final String NAME = "xiaobai_img";
    private static final String AVATAR = "avatar";

    @ApiOperation("跳转到图片管理页面")
    @GetMapping("/toImage")
    public String toImage(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "/error/403";
        }
        Integer userId = JwtUtils.getUserId(JwtUtils.getToken(cookies));
        model.addAttribute("userId", userId);
        return "privateImage";
    }

    @CrossOrigin
    @ApiOperation("读取分页查找所有目录")
    @GetMapping("/file/getAllDir")
    @ResponseBody
    public List<DirVo> getAllDir(HttpServletRequest request, Integer pageNumber, String s){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        String username = JwtUtils.getUsername(JwtUtils.getToken(cookies));
        String key = DigestUtils.md5Hex(username + NAME);
        File file = new File(directory + key);
        return fileService.getAllDir(file.listFiles(), s, pageNumber);
    }

    @CrossOrigin
    @ApiOperation("读取目录个数")
    @GetMapping("/file/getCountDir")
    @ResponseBody
    public Integer getDirCount(HttpServletRequest request, String s){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return 0;
        }
        String username = JwtUtils.getUsername(JwtUtils.getToken(cookies));
        String key = DigestUtils.md5Hex(username + NAME);
        File file = new File(directory + key);
        return fileService.getCountDir(file.listFiles(), s);
    }

    @ApiOperation("创建文件夹")
    @GetMapping("/file/createMyDir")
    @ResponseBody
    public String createMyDir(@RequestParam String dir, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "#未登录";
        }
        if (AVATAR.equals(dir)){
            return "#不能起:avatar作为目录";
        }
        String username = JwtUtils.getUsername(JwtUtils.getToken(cookies));
        String key = DigestUtils.md5Hex(username + NAME);
        String[] split = dir.split("/");
        File file = new File(directory + key + "//" + split[0]);
        if (file.exists()){
            return dir + "#目录已经存在";
        }
        else {
            return file.mkdirs() ? split[0] + "/" : "#添加失败";
        }
    }

    @ApiOperation("修改文件目录名")
    @PostMapping("/file/updateDirName")
    @ResponseBody
    public String updateDirectoryName(String newDir, String oldDir, HttpServletRequest request){

        // 认证层
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "#未登录";
        }
        if (AVATAR.equals(newDir)){
            return "#不能起:avatar作为目录";
        }
        // 源路径
        String username = JwtUtils.getUsername(JwtUtils.getToken(cookies));
        String key = DigestUtils.md5Hex(username + NAME);
        File file = new File(directory + key + "//" + oldDir);
        File newFile = new File(directory + key + "//" + newDir.split("/")[0]);
        return file.renameTo(newFile) ? "修改成功" : "#修改失败";
    }

    @CrossOrigin
    @ApiOperation("读取所有文件")
    @GetMapping("/file/getAllFile")
    @ResponseBody
    public List<FileVo> getAllFiles(HttpServletRequest request,
                                    @RequestParam String dir,
                                    @RequestParam String s,
                                    @RequestParam Integer pageNumber){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        String username = JwtUtils.getUsername(JwtUtils.getToken(cookies));
        String key = DigestUtils.md5Hex(username + NAME);
        File file = new File(directory + key + "//" + dir);
        return fileService.getAllFiles(file.listFiles(), s, pageNumber, url + key + "/" + dir + "/");
    }

    @CrossOrigin
    @ApiOperation("统计文件个数")
    @GetMapping("/file/getCountFile")
    @ResponseBody
    public Integer getCountFiles(HttpServletRequest request, @RequestParam String dir, @RequestParam String s){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return null;
        }
        String username = JwtUtils.getUsername(JwtUtils.getToken(cookies));
        String key = DigestUtils.md5Hex(username + NAME);
        File file = new File(directory + key + "//" + dir);
        return fileService.getCountFiles(file.listFiles(), s.toString());
    }

    @ApiOperation("单文件上传")
    @PostMapping("/file/uploadOneFile")
    @ResponseBody
    public String uploadOneFile(MultipartFile file, String userDir, HttpServletRequest request) throws IOException {
        // 认证层
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "#用户未登录";
        }
        String username = JwtUtils.getUsername(JwtUtils.getToken(cookies));
        // 合法层
        if (file.isEmpty() || file.getSize() <= 0){
            return "#文件为空，不能上传";
        }
        String key = DigestUtils.md5Hex(username + NAME);
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
        if (fileService.uploadFile(str, file)){
            Thumbnails.of(str)
                    .scale(0.25f)
                    .toFile(newFile + "//" + originFileName);
            return originFileName;
        }
        return  "#上传失败";
    }

    @ApiOperation("上传头像")
    @PostMapping("/file/uploadAvatar")
    @ResponseBody
    public String uploadAvatar(MultipartFile file, HttpServletRequest request) throws IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "#用户未登录";
        }
        if (file.isEmpty() || file.getSize() <= 0){
            return "#文件为空，不能上传";
        }
        String key = DigestUtils.md5Hex(JwtUtils.getUsername(JwtUtils.getToken(cookies)) + NAME);
        String newFilePath = directory
                + key
                + "//" + AVATAR;
        File newFile = new File(newFilePath);
        if (!newFile.exists()){
            return "#文件不存在，请联系管理员";
        }
        String originFileName = file.getOriginalFilename();
        assert originFileName != null;
        String suffix = originFileName.substring(originFileName.lastIndexOf(".") + 1);
        if (!("jpg".equals(suffix) || "png".equals(suffix) || "gif".equals(suffix))){
            return "#文件类型错误";
        }
        String str = newFilePath + "//" + originFileName;
        if (!fileService.uploadFile(str, file)){
            return "#文件上传失败";
        }
        Thumbnails.of(str)
                .size(200, 200)
                .toFile(newFile + "//" + originFileName);
        Integer userId = JwtUtils.getUserId(JwtUtils.getToken(cookies));
        User user = new User();
        user.setUserId(userId);
        String ava =  url + key + "/avatar/" + originFileName;
        user.setUserAvatar(ava);
        return userService.updateUser(user) == -1 ? "#文件上传失败" : ava;
    }

    @ApiOperation("多文件上传")
    @PostMapping("/file/uploadMoreFile")
    @ResponseBody
    public String uploadMoreFile(MultipartFile[] files, String userDir, HttpServletRequest request){
        // 认证层
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "用户未登录";
        }
        String username = JwtUtils.getUsername(JwtUtils.getToken(cookies));
        // 合法层
        if (files == null || files.length == 0){
            return "文件为空，不能上传";
        }
        String key = DigestUtils.md5Hex(username + NAME);
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

    @ApiOperation("修改文件名")
    @PostMapping("/file/updateFileName")
    @ResponseBody
    public String updateFileName(String newName, String oldName, String userDir,
                                 HttpServletRequest request){
        // 认证层
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "#未登录";
        }
        String username = JwtUtils.getUsername(JwtUtils.getToken(cookies));
        String key = DigestUtils.md5Hex(username + NAME);
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

    @ApiOperation("删除文件或文件夹")
    @GetMapping("/file/deleteFile")
    @ResponseBody
    public String deleteFile(String path, HttpServletRequest request){
        // 认证层
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "#未登录";
        }
        // 合法层
        if ("".equals(path)){
            return "#根目录不给删除";
        }
        String username = JwtUtils.getUsername(JwtUtils.getToken(cookies));
        String key = DigestUtils.md5Hex(username + NAME);
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

}
