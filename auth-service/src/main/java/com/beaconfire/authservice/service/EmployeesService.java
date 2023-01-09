package com.beaconfire.authservice.service;

import com.beaconfire.authservice.dao.EmployeesDAO;
import com.beaconfire.authservice.entity.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesService {
    private final EmployeesDAO employeesDAO;

    @Autowired
    public EmployeesService(EmployeesDAO employeesDAO) {
        this.employeesDAO = employeesDAO;
    }

    @Transactional
    public List<Employees> getAllEmployees() {
        return employeesDAO.getAllEmployees();
    }

    @Transactional
    public Employees findEmployeeById(Integer employeeId) {
        return employeesDAO.findEmployeeById(employeeId);
    }

    @Transactional
    public Optional<Employees> findEmployeeByEmail(String email) {
        return employeesDAO.loadEmployeeByEmail(email);
    }

    @Transactional
    public Integer addNewEmployee(Employees employee) {
        return employeesDAO.addNewEmployee(employee);
    }

    @Transactional
    public void updateEmployee(Employees employee) {
        employeesDAO.updateEmployee(employee);
    }

    @Transactional
    public void deleteEmployee(Employees employee) {
        employeesDAO.deleteEmployee(employee);
    }
}
