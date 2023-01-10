package com.beaconfire.employeeservice.controller;

import com.beaconfire.employeeservice.entity.Employee;
import com.beaconfire.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @ResponseBody
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/getEmployeeById")
    @ResponseBody
    public Employee getEmployeeById(Integer id) {
        return employeeService.getEmployeeById(id);
    }



}
