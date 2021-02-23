package net.xiaobais.xiaobai.service;

/**
 *
 * 验证码service
 *
 * @Author xiaobai
 * @Date 2021/2/22 14:01
 * @Version 1.0
 */
public interface CodeService {

    /**
     * 根据email，生成验证码
     * @param email email
     * @return code
     */
    String generateCode(String email);


    /**
     * 根据email和email是否匹配
     * @param email email
     * @param code code
     * @return boolean
     */
    boolean verifyCode(String email, String code);
}
