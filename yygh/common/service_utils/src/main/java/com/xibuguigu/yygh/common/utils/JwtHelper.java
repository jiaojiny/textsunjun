package com.xibuguigu.yygh.common.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xibuguigu.yygh.common.exception.YyghException;
import com.xibuguigu.yygh.common.result.ResultCode;
import io.jsonwebtoken.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtHelper {


    static String subject = "YYGH-USER";
    static String key = "atguigu123";
    static long time = 1000 * 60 * 60 * 24;

    /**
     * 生成jwt
     * @param userId 用户id，会作为jwt的载荷存入
     * @param userName 用户名，会作为jwt的载荷存入
     * @return 得到令牌
     */
    public static String createToken(Long userId, String userName) {

        JwtBuilder builder = Jwts.builder();
        String token = builder
                //设置jwt头
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                //data
                .setSubject(subject)//主题
                .setExpiration(new Date(System.currentTimeMillis() + time)) //过期时间
                .claim("userId", userId)
                .claim("userName", userName)
                //计算签名hash
                .signWith(SignatureAlgorithm.HS256, key.getBytes(StandardCharsets.UTF_8))
                .compact();

        return token;
    }

    /**
     * 解析jwt
     * @param token
     * @return 得到jwt载荷（令牌中存储的数据）
     */
    public static Claims parseToken(String token) {

        //获取jwt解析器
        JwtParser parser = Jwts.parser();
        //设置验签秘钥
        JwtParser jwtParser = parser.setSigningKey(key.getBytes(StandardCharsets.UTF_8));
        //使用验签秘钥进行验签并解析jwt
        Jws<Claims> claimsJws = null;
        try {
            claimsJws = jwtParser.parseClaimsJws(token);
        } catch (Exception e) {
            throw new YyghException(ResultCode.ERROR, "需要登录", e);
        }
        //解析jwt数据
        //String signature = claimsJws.getSignature();
        //JwsHeader header = claimsJws.getHeader();
        //String alg = (String)header.get("alg");
        //载荷信息
        Claims body = claimsJws.getBody();
        //String userName = (String)body.get("userName");
        return body;
    }

    /**
     * 通过token获取userId
     * @param token
     * @return
     */
    public static Long getUserId(String token) {
        if(StringUtils.isEmpty(token)){
            throw new YyghException(ResultCode.ERROR, "需要登录");
        }
        Claims claims = parseToken(token);
        Integer userId = (Integer)claims.get("userId");
        return userId.longValue();
    }

    public static void main(String[] args) {

        String token = createToken(1L, "tom");
        System.out.println(token);

        Claims body = parseToken(token);

        Long userId = getUserId(token);
        System.out.println(userId);
    }
}

