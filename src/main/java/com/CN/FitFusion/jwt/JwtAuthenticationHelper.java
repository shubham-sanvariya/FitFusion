package com.CN.FitFusion.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtAuthenticationHelper {

    private final static String TOKEN_SECRE_STRING = "thisisacodingninjasdemonstrationforsecretkeyinspringsecurityjsonwebtokenauthentication";
    
    public Claims getClaimsFromToken(String token){
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(TOKEN_SECRE_STRING.getBytes())
                            .build()
                            .parseClaimsJws(token)
                            .getBody();

            return claims;
    }

    public String getUserNameFromToken(String token){
        Claims claims = getClaimsFromToken(token);

        return claims.getSubject();
    }

    public boolean isTokenExpired(String token){
        Claims claims = getClaimsFromToken(token);
        Date expiringDate = claims.getExpiration();

        return expiringDate.before(new Date());
    }
}
