package com.CN.FitFusion.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtAuthenticationHelper {

    private final static String TOKEN_SECRE_STRING = "thisisacodingninjasdemonstrationforsecretkeyinspringsecurityjsonwebtokenauthentication";
    private final static long JWT_TOKEN_VALIDITY = 60*60;

    public String generateToken(UserDetails userDetails){
        Map<String ,Object> claims = new HashMap<>();

        return Jwts.builder().setClaims(claims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                    .signWith(new SecretKeySpec(TOKEN_SECRE_STRING.getBytes(),
                         SignatureAlgorithm.HS512.getJcaName()),SignatureAlgorithm.HS512)
                    .compact();
    }
    
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
