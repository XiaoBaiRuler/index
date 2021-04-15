package net.xiaobais.xiaobai.service;

/**
 * 邮箱service
 * @Author xiaobai
 * @Date 2021/2/22 17:15
 * @Version 1.0
 */
public interface EmailService {

    /**
     * 发送验证链接到邮箱
     * @param email email
     * @return boolean
     */
    boolean sendEmailVerifyCode(String email);

    /**
     * 发送验证码到邮箱
     * @param email email
     * @return boolean
     */
    boolean sendEmailCode(String email);
}
