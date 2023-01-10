package com.beaconfire.employeeservice.dao.impl;

import com.beaconfire.employeeservice.dao.AbstractHibernateDAO;
import com.beaconfire.employeeservice.dao.EmployeeDAO;
import com.beaconfire.employeeservice.entity.Employee;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("employeeDAO")
public class EmployeeDaoImpl extends AbstractHibernateDAO<Employee> implements EmployeeDAO {

    public EmployeeDaoImpl() {
        setClazz(Employee.class);
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        return findById(id);
    }

    @Override
    public void addEmployee(Employee employee) {
        add(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return getCurrentSession().createQuery("from employees").list();
    }

    @Override
    public Boolean deleteEmployee(Integer id) {
        Query query = getCurrentSession().createQuery("delete from employees where id = :id");
        query.setInteger("id", id);
        return query.executeUpdate() != 0;
    }
}