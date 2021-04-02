package net.xiaobais.xiaobai.filter;

import lombok.SneakyThrows;
import net.xiaobais.xiaobai.entity.UserEntity;
import net.xiaobais.xiaobai.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Author xiaobai
 * @Date 2021/2/19 20:56
 * @Version 1.0
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 获取授权管理
     */
    private AuthenticationManager authenticationManager;


    public JwtLoginFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/login");
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || password == null){
            response.sendRedirect("/toLogin");
            return null;
        }
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password,
                        new ArrayList<>()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException {
        UserEntity userEntity = (UserEntity) authResult.getPrincipal();
        String jwtToken = JwtUtils.generateJsonWebToken(userEntity);
        Cookie cookie = new Cookie("token", jwtToken);
        response.addCookie(cookie);
        response.sendRedirect("/index");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        response.sendRedirect("/toLogin");
    }

}
