package com.alexpozdnyakov.spring.rest.service;

import com.alexpozdnyakov.spring.rest.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();
    public Employee getEmployee(int id);
    public void saveEmployee(Employee employee);
    public void deleteEmployee(int id);

}
