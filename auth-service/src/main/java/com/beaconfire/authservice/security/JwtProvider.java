package com.beaconfire.authservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtProvider {
    @Value("${security.jwt.token.key}")
    private String key;

    @Value("${security.jwt.token.expires}")
    private String minutesExpired;

    // create JWT Token from a UserDetail
    public String createToken(AuthUserDetail authUserDetail) {
        Claims claims = Jwts.claims().setSubject(authUserDetail.getUsername()); // user identifier
        claims.put("permissions", authUserDetail.getAuthorities()); // user permission
        claims.put("employeeId", authUserDetail.getEmployeeId());
        claims.put("expireInMilliseconds", System.currentTimeMillis() + (Long.parseLong(minutesExpired) * 60 * 1000)); // set token to expire after certain minutes

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public Optional<AuthUserDetail> resolveToken(HttpServletRequest request) {
        String prefixedToken = request.getHeader("Authorization"); // extract the token
        String token = prefixedToken.substring(7); // skip the Bearer thing

        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody(); // decode

        // get the employee's username/email
        String username = claims.getSubject();
        List<LinkedHashMap<String, String>> permissions = (List<LinkedHashMap<String, String>>) claims.get("permissions");

        // get the employee's authorities
        List<GrantedAuthority> authorities = permissions.stream()
                .map(p -> new SimpleGrantedAuthority(p.get("authority")))
                .collect(Collectors.toList());

        return Optional.of(AuthUserDetail.builder()
                .username(username)
                .authorities(authorities)
                .employeeId((Integer) claims.get("employeeId"))
                .expireInMilliseconds((long) claims.get("expireInMilliseconds"))
                .build());
    }
}
