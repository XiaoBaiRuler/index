package net.xiaobais.xiaobai.config;

import net.xiaobais.xiaobai.filter.JwtLoginFilter;
import net.xiaobais.xiaobai.filter.JwtValidationFilter;
import net.xiaobais.xiaobai.mapper.AuthorityMapper;
import net.xiaobais.xiaobai.model.Authority;
import net.xiaobais.xiaobai.model.AuthorityExample;
import net.xiaobais.xiaobai.service.impl.MemberUserDetailsServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author xiaobai
 * @Date 2021/2/19 20:32
 * @Version 1.0
 */
@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MemberUserDetailsServiceImpl memberUserDetailsService;
    @Resource
    private AuthorityMapper authorityMapper;

    private static final String KEY = "xiaobai";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberUserDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return DigestUtils.md5Hex(charSequence + KEY);
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                String rawPassword = DigestUtils.md5Hex(charSequence + KEY);
                return rawPassword.equals(s);
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 防止编码错误
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        http.addFilterBefore(encodingFilter, CsrfFilter.class);


        AuthorityExample authorityExample = new AuthorityExample();
        List<Authority> authorityList = authorityMapper.selectByExample(authorityExample);

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
                expressionInterceptUrlRegistry = http.authorizeRequests();
        authorityList.forEach(authority -> {
            expressionInterceptUrlRegistry.antMatchers(authority.getAuthorityUrl())
                    .hasAnyAuthority(authority.getAuthorityTag());
        });

        expressionInterceptUrlRegistry
                .antMatchers(HttpMethod.GET, "/*.html",
                        "/favicon.ico", "/**/*.html",
                        "/**/*.css", "/**/*.js", "/swagger-resources/**",
                        "v2/api-docs/**", "/webjars/**",
                        "/item/**", "/img/**").permitAll()
                .antMatchers("/index", "/").permitAll()
                .antMatchers("/toLogin").permitAll()
                .antMatchers("/toSign").permitAll()
                .antMatchers("/sign").permitAll()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/error/**").permitAll()
                .antMatchers("/**").fullyAuthenticated()
                .and()
                .formLogin().loginPage("/toLogin").loginProcessingUrl("/login")
                .and().csrf().disable()
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .addFilter(new JwtLoginFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
