package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.model.User;
import net.xiaobais.xiaobai.service.CodeService;
import net.xiaobais.xiaobai.service.EmailService;
import net.xiaobais.xiaobai.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author xiaobai
 * @Date 2021/2/22 17:20
 * @Version 1.0
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String from;
    @Value("${mail.prefixUrl}")
    private String prefixUrl;
    @Resource
    private CodeService codeService;
    @Resource
    private JavaMailSender mailSender;
    @Resource
    private UserService userService;

    @Override
    public boolean sendEmailVerifyCode(String email) {
        if (email.equals(from)){
            return false;
        }
        try {
            String code = codeService.generateCode(email);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("xiaobais.net: 验证链接2分钟内有效");
            mailMessage.setText(prefixUrl + "?email=" + email + "&code=" + code);
            mailMessage.setTo(email);
            mailMessage.setFrom(from);
            mailSender.send(mailMessage);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean sendEmailCode(String email) {
        if (email.equals(from)){
            return false;
        }
        try {
            String code = codeService.generateCode(email);
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("xiaobais.net: 验证码2分钟内有效:[" + code + "]");
            mailMessage.setText(code);
            mailMessage.setTo(email);
            mailMessage.setFrom(from);
            mailSender.send(mailMessage);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean sendMessage(Integer userId, String message) {
        User user = userService.getUserById(userId);
        if (!user.getIsAuth()){
            return false;
        }
        if (user.getUserEmail().equals(from)){
            return false;
        }
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("xiaobais.net");
            mailMessage.setText(message);
            mailMessage.setTo(user.getUserEmail());
            mailMessage.setFrom(from);
            mailSender.send(mailMessage);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
