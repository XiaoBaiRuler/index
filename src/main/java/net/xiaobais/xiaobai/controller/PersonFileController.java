package net.xiaobais.xiaobai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.xiaobais.xiaobai.model.Node;
import net.xiaobais.xiaobai.model.User;
import net.xiaobais.xiaobai.service.FileService;
import net.xiaobais.xiaobai.service.PublicNodeService;
import net.xiaobais.xiaobai.service.UserService;
import net.xiaobais.xiaobai.utils.JwtUtils;
import net.xiaobais.xiaobai.vo.DirVo;
import net.xiaobais.xiaobai.vo.FileVo;
import net.xiaobais.xiaobai.vo.SimpleNodeVo;
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
import java.util.ArrayList;
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
    private PublicNodeService nodeService;
    @Resource
    private FileService fileService;

    @Value("${file.dir}")
    private String directory;

    private static final String NAME = "xiaobai_img";

    private static final String URL = "http://www.xiaobais.net:8080/image/";

    @ApiOperation("跳转到图片管理页面")
    @GetMapping("/toImage")
    public String toImage(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "/error/403";
        }
        Integer userId = JwtUtils.getUserId(cookies[0].getValue());
        model.addAttribute("userId", userId);
        model.addAttribute("mostCollect", nodeToSimpleNodeVo(nodeService.findNodeByTopCollect(5)));
        model.addAttribute("mostStar", nodeToSimpleNodeVo(nodeService.findNodeByTopStar(5)));
        return "privateImage";
    }

    @ApiOperation("单文件上传")
    @PostMapping("/file/uploadOneFile")
    @ResponseBody
    public String uploadOneFile(MultipartFile file, String userDir, HttpServletRequest request){
        // 认证层
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "#用户未登录";
        }
        String username = JwtUtils.getUsername(cookies[0].getValue());
        // 合法层
        if (file.isEmpty()){
            return "#文件为空，不能上传";
        }
        if (file.getSize() <= 0){
            return "#上传文件不能小于0KB";
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
        return fileService.uploadFile(str, file) ? originFileName : "#上传失败";
    }

    @ApiOperation("上传头像")
    @PostMapping("/file/uploadAvatar")
    @ResponseBody
    public String uploadAvatar(MultipartFile file, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "#用户未登录";
        }
        if (file.isEmpty() || file.getSize() <= 0){
            return "#文件为空，不能上传";
        }
        String key = DigestUtils.md5Hex(JwtUtils.getUsername(cookies[0].getValue()) + NAME);
        String newFilePath = directory
                + key
                + "//" + "avatar";
        File newFile = new File(newFilePath);
        if (!newFile.exists()){
            return "文件不存在，请联系管理员";
        }

        String originFileName = file.getOriginalFilename();
        assert originFileName != null;
        String suffix = originFileName.substring(originFileName.lastIndexOf(".") + 1);
        if (!("jpg".equals(suffix) || "png".equals(suffix) || "gif".equals(suffix))){
            return "文件类型错误";
        }

        if (fileService.uploadFile(newFilePath + "//" + originFileName, file)){
            return "文件上传失败";
        }
        Integer userId = JwtUtils.getUserId(cookies[0].getValue());
        User user = new User();
        user.setUserId(userId);
        user.setUserAvatar(URL + key + "/avatar/" + originFileName);
        return userService.updateUser(user) == -1 ? "文件上传失败" : "文件上传成功";
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
        String username = JwtUtils.getUsername(cookies[0].getValue());
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

    @ApiOperation("创建文件夹")
    @GetMapping("/file/createMyDir")
    @ResponseBody
    public String createMyDir(@RequestParam String dir, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "#未登录";
        }
        String username = JwtUtils.getUsername(cookies[0].getValue());
        String key = DigestUtils.md5Hex(username + NAME);
        String[] split = dir.split("/");
        File file = new File(directory + key + "//" + split[0]);
        if (file.exists()){
            return dir + "#目录已经存在";
        }
        else {
            return file.mkdirs() ? split[0] : "#添加失败";
        }
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
        String username = JwtUtils.getUsername(cookies[0].getValue());
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

    @ApiOperation("修改文件目录名")
    @PostMapping("/file/updateDirName")
    @ResponseBody
    public String updateDirectoryName(String newDir, String oldDir, HttpServletRequest request){

        // 认证层
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "#未登录";
        }
        // 源路径
        String username = JwtUtils.getUsername(cookies[0].getValue());
        String key = DigestUtils.md5Hex(username + NAME);
        File file = new File(directory + key + "//" + oldDir);
        File newFile = new File(directory + key + "//" + newDir);
        return file.renameTo(newFile) ? "修改成功" : "#修改失败";
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
        String username = JwtUtils.getUsername(cookies[0].getValue());
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

    @CrossOrigin
    @ApiOperation("读取所有目录")
    @GetMapping("/file/getAllDir")
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
                DirVo dirVo = new DirVo(value.getName());
                dirVos.add(dirVo);
            }
        }
        return dirVos;
    }

    @CrossOrigin
    @ApiOperation("读取所有文件")
    @GetMapping("/file/getAllFile")
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
                fileVo.setFileName(value.getName());
                String type= value.getName().substring(value.getName().lastIndexOf(".")+1);
                if ("jpg".equals(type) || "png".equals(type) || "gif".equals(type)){
                    fileVo.setFileUrl(URL + key + "/" + dir + "/" + value.getName());
                }
                else{
                    fileVo.setFileUrl("/img/img.png");
                }
                fileVo.setMkUrl("![" + value.getName() + "](" + fileVo.getFileUrl() + ")");
                fileVos.add(fileVo);
            }
        }
        return fileVos;
    }

    private List<SimpleNodeVo> nodeToSimpleNodeVo(List<Node> nodes){
        List<SimpleNodeVo> simpleNodeVos = new ArrayList<>(nodes.size());
        nodes.forEach(node -> {
                    SimpleNodeVo vo = new SimpleNodeVo();
                    vo.setUrl("/public/node/" + node.getNodeId());
                    vo.setTitle(node.getNodeName());
                    simpleNodeVos.add(vo);
                }
        );
        return simpleNodeVos;
    }

}
