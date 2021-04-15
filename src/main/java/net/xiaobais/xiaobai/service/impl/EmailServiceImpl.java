package net.xiaobais.xiaobai.service.impl;

import net.xiaobais.xiaobai.service.CodeService;
import net.xiaobais.xiaobai.service.EmailService;
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

    @Override
    public boolean sendEmailVerifyCode(String email) {
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
}
