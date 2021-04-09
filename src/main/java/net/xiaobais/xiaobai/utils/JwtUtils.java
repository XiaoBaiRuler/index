package net.xiaobais.xiaobai.utils;

import com.alibaba.fastjson.JSONArray;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.xiaobais.xiaobai.entity.UserEntity;
import net.xiaobais.xiaobai.vo.UserVo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xiaobai
 * @Date 2021/2/12 12:17
 * @Version 1.0
 */
public class JwtUtils {

    private static final String ROLE_CLAIMS = "roles";

    private static final String SUBJECT = "xiaobai";

    private static final long EXPIRATION = 1000 * 24 * 60 * 60 * 7;

    private static final String KEY = "888xiaobai#huang666";

    /**
     * 根据UserEntity生成token
     * @param user user
     * @return String
     */
    public static String generateJsonWebToken(UserEntity user){
        System.out.println(user.getUsername());
        return Jwts
                .builder()
                .setSubject(SUBJECT)
                .claim(ROLE_CLAIMS, user.getAuthorities())
                .claim("id", user.getUserId())
                .claim("username", user.getUsername())
                .claim("avatar", user.getUserAvatar())
                .claim("indexId", user.getIndexId())
                .claim("email", user.getUserEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }

    /**
     * 根据用户名和权限生成token
     * @param username username
     * @param role roles
     * @return String
     */
    public static String createToken(String username, String role){
        Map<String, Object> map = new HashMap<>(16);
        map.put(ROLE_CLAIMS, role);
        return Jwts.builder()
                .setSubject(username)
                .setClaims(map)
                .claim("username", username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }

    /**
     * 验证token是否有效
     * @param token token
     * @return Claims or null
     */
    public static Claims checkJwt(String token){
        try {
            return Jwts
                    .parser()
                    .setSigningKey(KEY)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据token查找用户名
     * @param token token
     * @return String
     */
    public static String getUsername(String token){
        Claims claims = Jwts.parser().
                setSigningKey(KEY).
                parseClaimsJws(token)
                .getBody();
        return claims.get("username").toString();
    }

    /**
     * 根据token查找用户Id
     * @param token token
     * @return Integer
     */
    public static Integer getUserId(String token){
        Claims claims = Jwts.parser().
                setSigningKey(KEY).
                parseClaimsJws(token)
                .getBody();
        return (Integer) claims.get("id");
    }

    /**
     * 根据token查找userVo
     * @param token token
     * @return UserVo
     */
    public static UserVo getUserVo(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody();
        UserVo userVo = new UserVo();
        userVo.setUsername((String) claims.get("username"));
        userVo.setUrl("/private/node/" + claims.get("indexId") + "/" + claims.get("id") + "?isUpdate=0");
        userVo.setImg((String) claims.get("avatar"));
        userVo.setUserId((Integer) claims.get("id"));
        userVo.setIndexId((Integer) claims.get("indexId"));
        userVo.setEmail((String) claims.get("email"));
        return userVo;
    }

    /**
     * 根据token查找用户权限
     * @param token token
     * @return List<SimpleGrantedAuthority>
     */
    public static List<SimpleGrantedAuthority> getUserRole(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody();
        List roles = (List) claims.get("roles");
        String json = JSONArray.toJSONString(roles);
        return JSONArray.parseArray(json, SimpleGrantedAuthority.class);
    }

    /**
     * 验证token是否过期
     * @param token token
     * @return boolean
     */
    public static boolean isExpiration(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(KEY).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }
}
