package com.beaconfire.authservice.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {
    private String email;
    private String password;
}
