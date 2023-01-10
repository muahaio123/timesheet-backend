package com.beaconfire.authservice.controller;

import com.beaconfire.authservice.domain.EmployeeRequest;
import com.beaconfire.authservice.domain.RegisterResponse;
import com.beaconfire.authservice.entity.Employees;
import com.beaconfire.authservice.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final EmployeesService employeesService;

    @Autowired
    public RegisterController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @PostMapping
    public ResponseEntity<RegisterResponse> registerNewUser(@RequestBody EmployeeRequest employeeRequest) {
        if (employeeRequest.getEmail() == null || employeeRequest.getEmail().length() < 3
                || employeeRequest.getPassword() == null || employeeRequest.getPassword().length() < 3)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RegisterResponse.builder()
                            .success(false)
                            .message("Please fill in both Email and Password! Both field must be at least 3 characters!")
                    .build());

        Employees newEmployee = Employees.builder()
                    .email(employeeRequest.getEmail())
                    .password(employeeRequest.getPassword())
                .build();

        try {
            newEmployee.setId(employeesService.addNewEmployee(newEmployee));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RegisterResponse.builder()
                    .success(false)
                    .message("Email Already Existed! Please use new Email!")
                    .build());
        }

        return ResponseEntity.ok(RegisterResponse.builder()
                        .success(true)
                        .message("New Employee account registered!")
                        .employee(newEmployee)
                .build());
    }
}
