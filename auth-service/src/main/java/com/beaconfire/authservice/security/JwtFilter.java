package com.beaconfire.authservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    @Autowired
    public JwtFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("In doFilterInternal!");
        System.out.println("URI: " + request.getRequestURI());

        if (!request.getRequestURI().contains("/login")) {
            // extract jwt from request, generate an AuthUserDetail object
            Optional<AuthUserDetail> authUserDetailOptional = jwtProvider.resolveToken(request);

            if (authUserDetailOptional.isPresent()) {
                AuthUserDetail authUserDetail = authUserDetailOptional.get();
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        authUserDetail.getUsername(),
                        null,
                        authUserDetail.getAuthorities()
                ); // generate authentication object

                // put authentication object in the security context
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
