package com.ljz.loverspace.utils.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    private static final String SECRET = "CodingLiOOT";
    private static final long EXPIRE_TIME = 10*60 * 60 * 1000;

    public static String sign(String username) {
        return JWT.create()
                .withClaim("username", username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static boolean verify(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static String getUsername(String token) {
        return JWT.decode(token).getClaim("username").asString();
    }
}
