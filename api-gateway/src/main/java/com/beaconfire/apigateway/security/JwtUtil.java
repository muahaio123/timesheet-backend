package com.beaconfire.apigateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    @Value("${security.jwt.token.key}")
    private String key;

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token.substring(7)).getBody();
    }

    private boolean isTokenExpired(String token) {
        return System.currentTimeMillis() > (long) this.getAllClaimsFromToken(token).get("expireInMilliseconds");
    }

    public boolean isInvalid(String token) { // condition for an INVALID JWT token
        return token == null || !token.startsWith("Bearer ") || StringUtils.countMatches(token, ".") != 2 || this.isTokenExpired(token); // JWT strings must contain exactly 2 period characters
    }
}
