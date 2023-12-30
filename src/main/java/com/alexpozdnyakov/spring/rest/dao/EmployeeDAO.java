package com.alexpozdnyakov.spring.rest.dao;

import com.alexpozdnyakov.spring.rest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Integer> {
}
