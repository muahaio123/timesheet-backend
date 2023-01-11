package com.beaconfire.profilecompositeservice.domain.EmployeeService;

import com.beaconfire.profilecompositeservice.entity.EmployeeService.Employee;
import lombok.*;

import java.util.List;

/**
 * @author Richard Zhang
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllEmployeesResponse {
    private List<Employee> employeeList;
}
