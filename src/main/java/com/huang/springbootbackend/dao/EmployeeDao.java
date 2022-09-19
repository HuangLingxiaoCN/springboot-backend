package com.huang.springbootbackend.dao;

import com.huang.springbootbackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


// With JpaRepository class, the EmployeeDao will have basic CRUD methods
public interface EmployeeDao extends JpaRepository<Employee, Long> {
}
