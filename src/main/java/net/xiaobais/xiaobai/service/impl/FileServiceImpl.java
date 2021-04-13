package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.service.FileService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

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
        return file.mkdirs();
    }
}
