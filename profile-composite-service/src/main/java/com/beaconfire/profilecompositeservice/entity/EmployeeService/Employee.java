package com.beaconfire.profilecompositeservice.entity.EmployeeService;

import lombok.*;

/**
 * @author Richard Zhang
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer id;
    private String email;
    private String password;
}
