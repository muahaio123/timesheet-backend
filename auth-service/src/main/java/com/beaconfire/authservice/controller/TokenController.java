package com.beaconfire.authservice.controller;

import com.beaconfire.authservice.security.AuthUserDetail;
import com.beaconfire.authservice.security.JwtProvider;
import com.beaconfire.authservice.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/token")
public class TokenController {
    private final EmployeesService employeesService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Autowired
    public TokenController(EmployeesService employeesService, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.employeesService = employeesService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping("/resolve")
    public AuthUserDetail resolveToken(HttpServletRequest request) {
        return jwtProvider.resolveToken(request).orElse(AuthUserDetail.builder().username("none info resolved").build());
    }
}
