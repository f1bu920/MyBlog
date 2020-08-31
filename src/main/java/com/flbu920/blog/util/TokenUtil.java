package com.flbu920.blog.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
@Data
public class TokenUtil {

    @Value("${blog.jwt.expire.token}")
    public long EXPIRE_TIME;//token到期时间默认15分钟，秒为单位

    @Value("${blog.jwt.expire.refresh}")
    public long REFRESH_EXPIRE_TIME;
    ;//RefreshToken到期时间为30分钟，秒为单位

    @Value("${blog.jwt.secret}")
    public String TOKEN_SECRET;  //密钥盐

    public String sign(Integer userId, Long currentTime) {

        Date expireTime = new Date(currentTime + EXPIRE_TIME);
        String token = JWT.create()
                .withClaim("userId", userId)
                .withClaim("currentTime", currentTime)
                .withExpiresAt(expireTime)
                .sign(Algorithm.HMAC256(TOKEN_SECRET));
        return token;
    }


    public Boolean verify(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).build();//创建token验证器
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        Date expiresAt = decodedJWT.getExpiresAt();
        if (expiresAt.before(new Date())) {
            log.info("token已过期");
            //token过期了
            return false;
        }
        log.info("token认证通过." + "Id: " + decodedJWT.getClaim("userId").asString() + "过期时间：      " + expiresAt);
        return true;
    }


    public static Integer getUserId(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim("userId").asInt();
    }

    public Long getCurrentTime(String token) {

        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim("currentTime").asLong();

    }

}
