package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.service.FileService;
import net.xiaobais.xiaobai.vo.DirVo;
import net.xiaobais.xiaobai.vo.FileVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/4/13 11:16
 * @Version 1.0
 */
@Service
public class FileServiceImpl implements FileService {

    @Value("${file.dir}")
    private String directory;

    private static final String NAME = "xiaobai_img";
    private static final String AVATAR = "avatar";

    @Override
    public boolean uploadFile(String path, MultipartFile file) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean createRootDirectory(String username) {
        String s = DigestUtils.md5Hex(username + NAME);
        File file = new File(directory + s);
        if (file.exists() || file.mkdirs()){
            File avatar = new File(directory + s + "//" + "avatar");
            return avatar.mkdirs();
        }
        else{
            return false;
        }
    }

    @Override
    public List<DirVo> getAllDir(File[] files, String s, Integer pageNumber) {
        if (files == null){
            return null;
        }
        pageNumber --;
        int count = pageNumber * 7;
        List<DirVo> dirVos = new ArrayList<>();
        if (!"".equals(s)){
            for (File value : files) {
                if (value.isDirectory() && !AVATAR.equals(value.getName()) && value.getName().contains(s)){
                    count --;
                    if (count < 0 && count >= -7){
                        DirVo dirVo = new DirVo(value.getName());
                        dirVos.add(dirVo);
                    }
                }
            }
        }
        else{
            for (File value : files) {
                if (value.isDirectory() && !AVATAR.equals(value.getName())){
                    count --;
                    if (count < 0 && count >= -7){
                        DirVo dirVo = new DirVo(value.getName());
                        dirVos.add(dirVo);
                    }
                }
            }
        }
        return dirVos;
    }

    @Override
    public Integer getCountDir(File[] files, String s) {
        if (files == null){
            return 0;
        }
        int count = 0;
        if (!"".equals(s)){
            for (File value : files) {
                if (value.isDirectory() && !AVATAR.equals(value.getName()) && value.getName().contains(s)){
                    count ++;
                }
            }
        }
        else{
            for (File value : files) {
                if (value.isDirectory() && !AVATAR.equals(value.getName())){
                    count ++;
                }
            }
        }
        return count / 7;
    }

    @Override
    public List<FileVo> getAllFiles(File[] files, String s, Integer pageNumber, String prePath) {
        if (files == null){
            return null;
        }
        pageNumber --;
        int count = pageNumber * 6;
        List<FileVo> fileVos = new ArrayList<>();
        if (!"".contains(s)){
            for (File value : files){
                if (value.isFile()){
                    if (value.getName().contains(s)){
                        count --;
                        if (count < 0 && count >= -6){
                            FileVo fileVo = new FileVo();
                            fileVo.setFileName(value.getName());
                            String type= value.getName().substring(value.getName().lastIndexOf(".")+1);
                            if ("jpg".equals(type) || "png".equals(type) || "gif".equals(type)){
                                fileVo.setFileUrl(prePath + value.getName());
                            }
                            else{
                                fileVo.setFileUrl("/img/img.png");
                            }
                            fileVo.setMkUrl("![" + value.getName() + "](" + fileVo.getFileUrl() + ")");
                            fileVos.add(fileVo);
                        }
                    }
                }
            }
        }
        else{
            for (File value : files){
                if (value.isFile()){
                    count --;
                    if (count < 0 && count >= -6){
                        FileVo fileVo = new FileVo();
                        fileVo.setFileName(value.getName());
                        String type= value.getName().substring(value.getName().lastIndexOf(".")+1);
                        if ("jpg".equals(type) || "png".equals(type) || "gif".equals(type)){
                            fileVo.setFileUrl(prePath + value.getName());
                        }
                        else{
                            fileVo.setFileUrl("/img/img.png");
                        }
                        fileVo.setMkUrl("![" + value.getName() + "](" + fileVo.getFileUrl() + ")");
                        fileVos.add(fileVo);
                    }
                }
            }
        }
        return fileVos;
    }

    @Override
    public Integer getCountFiles(File[] files, String s) {
        if (files == null){
            return 0;
        }
        int count = 0;
        if (!"".contains(s)){
            for (File value : files){
                if (value.isFile() && value.getName().contains(s)){
                    count ++;
                }
            }
        }
        else{
            for (File value : files){
                if (value.isFile()){
                    count ++;
                }
            }
        }
        return count / 6;
    }

}
