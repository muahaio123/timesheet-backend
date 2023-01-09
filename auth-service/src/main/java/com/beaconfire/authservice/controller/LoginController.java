package com.beaconfire.authservice.controller;

import com.beaconfire.authservice.domain.EmployeeRequest;
import com.beaconfire.authservice.domain.EmployeeResponse;
import com.beaconfire.authservice.entity.Employees;
import com.beaconfire.authservice.security.AuthUserDetail;
import com.beaconfire.authservice.security.JwtProvider;
import com.beaconfire.authservice.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final EmployeesService employeesService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Autowired
    public LoginController(EmployeesService employeesService, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.employeesService = employeesService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping
    public EmployeeResponse authenticateUser(@RequestBody EmployeeRequest employeeRequest) {
        System.out.println("Authenticating User!");
        System.out.println("Content in body: " + employeeRequest.getEmail() + " | " + employeeRequest.getPassword());

        Authentication authentication;
        try{
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(employeeRequest.getEmail(), employeeRequest.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Provided credential is invalid.");
        }

        // Found the user we are looking for
        Employees employeeRequested = employeesService.findEmployeeByEmail(employeeRequest.getEmail()).get();
        AuthUserDetail authUserDetail = (AuthUserDetail) authentication.getPrincipal();
        authUserDetail.setEmployeeId(employeeRequested.getId());

        String token = jwtProvider.createToken(authUserDetail);

        return EmployeeResponse.builder()
                .message("Authentication successful! Welcome " + authUserDetail.getUsername())
                .token(token)
                .build();
    }
}
