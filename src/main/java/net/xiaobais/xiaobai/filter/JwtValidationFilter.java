package net.xiaobais.xiaobai.filter;

import net.xiaobais.xiaobai.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/2/19 21:00
 * @Version 1.0
 */
public class JwtValidationFilter extends BasicAuthenticationFilter {

    public JwtValidationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Cookie[] cookies = request.getCookies();
        // 过滤请求验证
        if (cookies == null){
            super.doFilterInternal(request, response, chain);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(cookies[0].getValue()));
        super.doFilterInternal(request, response, chain);
    }

    /**
     * 验证token, 并且返回权限
     * @param token token
     * @return UsernamePasswordAuthenticationToken
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token){
        String username = JwtUtils.getUsername(token);
        if (username != null){
            List<SimpleGrantedAuthority> userRoles = JwtUtils.getUserRole(token);
            return new UsernamePasswordAuthenticationToken(username, null, userRoles);
        }
        return null;
    }
}
