package com.beaconfire.authservice.dao;

import com.beaconfire.authservice.entity.Employees;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("employeesDAO")
public class EmployeesDAO extends AbstractDAO<Employees> {
    public EmployeesDAO() {
        super.setClazz(Employees.class);
    }

    public List<Employees> getAllEmployees() {
        return super.getAll();
    }

    public Employees findEmployeeById(Integer id) {
        return super.findById(id);
    }

    public Integer addNewEmployee(Employees employee) {
        return super.addObject(employee);
    }

    public void updateEmployee(Employees employee) {
        super.updateObject(employee);
    }

    public void deleteEmployee(Employees employee) {
        super.deleteObject(employee);
    }

    public Optional<Employees> loadEmployeeByEmail(String email) {
        return super.getAll().stream().filter(employee -> email.equals(employee.getEmail())).findAny();
    }
}
