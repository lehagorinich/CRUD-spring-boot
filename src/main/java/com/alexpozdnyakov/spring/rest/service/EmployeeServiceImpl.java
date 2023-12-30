package com.alexpozdnyakov.spring.rest.service;

import com.alexpozdnyakov.spring.rest.dao.EmployeeDAO;
import com.alexpozdnyakov.spring.rest.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@EnableTransactionManagement
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {

        return employeeDAO.findAll();
    }

    @Override
    @Transactional
    public Employee getEmployee(int id) {
        return employeeDAO.findById(id).get();
    }

    @Transactional
    @Override
    public void saveEmployee(Employee employee) {
        employeeDAO.save(employee);
    }

    @Transactional
    @Override
    public void deleteEmployee(int id) {
        employeeDAO.deleteById(id);
    }

}
