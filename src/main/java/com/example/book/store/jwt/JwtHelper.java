package com.example.book.store.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import net.bytebuddy.implementation.bytecode.Throw;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtHelper {

    public static final String COMMON_KEY = "c2VjcmV0LWtleS1qd3QtdG9rZW4=##12323213";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(COMMON_KEY.getBytes(StandardCharsets.UTF_8));

    public static String createToken(Map<String,Object> body){

        long present = new Date().getTime();
        long expireTime = 15000L;
        return Jwts.builder()
                .setClaims(body)
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .setIssuer((String)body.get("username"))
                .setIssuedAt(new Date(present))
                .setExpiration(new Date(present + expireTime))
                .compact();
    }

    private static Map<String,Object> decodeToken(String token){
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build();
        try {
            return parser.parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e){
            System.out.println("Token is expired");
        }catch (UnsupportedJwtException e){
            System.out.println("Token is unsupported");
        }catch (MalformedJwtException e){
            System.out.println("Token is malformed");
        }catch (SignatureException e){
            System.out.println("Signature Key is invalid");
        }catch (IllegalArgumentException e){
            System.out.println("Token is illegal");
        }
        return null;
    }
}
