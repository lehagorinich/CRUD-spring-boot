package com.alexpozdnyakov.spring.rest.controller;


import com.alexpozdnyakov.spring.rest.entity.Employee;
import com.alexpozdnyakov.spring.rest.exception_handling.NoSuchEmployeeException;
import com.alexpozdnyakov.spring.rest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    private EmployeeService employeeService;

    @Autowired
    public MyRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        //List<Employee> allEmployees=employeeService.getAllEmployees();
        //return allEmployees;
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")

    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployee(id);


        if (employee == null) {
            throw new NoSuchEmployeeException("There is no enployee whith id " + id + " int Database");
        }
        return employee;


    }
    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return employee;
    }
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id){
        Employee employee=employeeService.getEmployee(id);
        if(employee==null){
            throw  new NoSuchEmployeeException("There is no enployee whith id " + id + " int Database");
        }
        employeeService.deleteEmployee(id);
        return "Employee with ID = "+id+" was deleted";
    }



}
