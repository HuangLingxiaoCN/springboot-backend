package com.huang.springbootbackend.service.impl;

import com.huang.springbootbackend.dao.EmployeeDao;
import com.huang.springbootbackend.entity.Employee;
import com.huang.springbootbackend.exception.ResourceNotFoundException;
import com.huang.springbootbackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
//        Optional<Employee> employee = employeeDao.findById(id);
//        if(employee.isPresent()) {
//            return employee.get();
//        }
//        else {
//            throw new ResourceNotFoundException("Employee", "id", id);
//        }
        return employeeDao
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {

        // we need to check whether employee with given id exists in DB or not
        Employee existingEmployee = employeeDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Id", id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        // save existing employee to DB
        employeeDao.save(existingEmployee);

        return existingEmployee;
    }

    @Override
    public void deleteEmployee(long id) {

        // check whether an employee exists in DB or not
        employeeDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Id", id));

        employeeDao.deleteById(id);
    }

}
