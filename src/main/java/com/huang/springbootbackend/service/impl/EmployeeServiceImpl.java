package com.huang.springbootbackend.service.impl;

import com.huang.springbootbackend.dao.EmployeeDao;
import com.huang.springbootbackend.entity.Employee;
import com.huang.springbootbackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    // From Spring 4.3, if a class configured as a Spring bean, has only one constructor
    // the @Autowired annotation can be omitted
    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeDao.save(employee);
    }

}
