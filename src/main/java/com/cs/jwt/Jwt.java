package com.cs.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.var;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SignatureException;
import java.util.Date;

public class Jwt {

    String secretString;

    public Jwt() {
        this.secretString = "my-super-secret-key-that-is-at-least-32-bytes-long!!";
    }

    String gen(int userId){
        Key key = Keys.hmacShaKeyFor(secretString.getBytes());
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + 3600_000; // 1小时后过期
        Date exp = new Date(expMillis);
        String token = Jwts.builder()
                .subject(String.valueOf(userId))                          // 标准字段：sub (用户标识)
//                .claim("userId", userId)                     // 自定义字段 不用也行
//                .claim("username", "exampleUser")
//                .claim("role", "admin")
                .issuedAt(now)                             // iat: 签发时间
                .expiration(exp)                            // exp: 过期时间
                .signWith(key)                              // 签名，默认使用HS256
                .compact();
        System.out.println("生成的token为"+token);
        return token;
    }


    boolean test(String token) {
        try {
            // 使用 UTF-8 固定字符集转换密钥
            var key = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));

            Claims claims = Jwts.parser()
                    .verifyWith(key) // 使用相同的密钥验证
                    .build()
                    .parseSignedClaims(token)    //不对的话会抛出异常
                    .getPayload();
            System.out.println("successful! userId: " + claims.getSubject());
            return true;
        } catch (ExpiredJwtException e) {
            // 令牌过期
            System.out.println("Token expired: " + e.getMessage());
            return false;
        } catch (MalformedJwtException e) {
            // 格式错误
            System.out.println("Malformed token: " + e.getMessage());
            return false;
        } catch (Exception e) {
            // 其他异常
            System.out.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        // 3. 解析并验证JWT（可选，演示用）
        Jwt jwt = new Jwt();
        String token=jwt.gen(3);
        jwt.test(token);
    }
}