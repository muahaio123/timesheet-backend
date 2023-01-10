package com.beaconfire.employeeservice.dao;


import com.beaconfire.employeeservice.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    Employee getEmployeeById(Integer id);

    void addEmployee(Employee employee);

    List<Employee> getAllEmployees();

//    List<Employee> getContactByEmployeeID(Integer id);

    Boolean deleteEmployee(Integer id);
}
