package com.beaconfire.authservice.domain;

import com.beaconfire.authservice.entity.Employees;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterResponse {
    private boolean success;
    private String message;
    private Employees employee;
}
