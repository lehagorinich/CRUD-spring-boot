package com.alexpozdnyakov.spring.rest.controller;

import com.alexpozdnyakov.spring.rest.entity.Employee;
import com.alexpozdnyakov.spring.rest.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class MyRestControllerTest {
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private MyRestController myRestController;

    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(myRestController).build();
    }

    @Test
    void showAllEmployees() throws Exception {
        List<Employee> employees = Arrays.asList(new Employee(1, "Alex", "Pozdnyakov", "IT", 500),
                new Employee(2, "Anna", "Kazennova", "Dance", 300));
        when(employeeService.getAllEmployees()).thenReturn(employees);
        mockMvc.perform(get("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alex"));
    }

    @Test
    void getEmployee() throws Exception {
        Employee employee = new Employee(1, "Alex", "Pozdnyakov", "IT", 500);
        when(employeeService.getEmployee(1)).thenReturn(employee);
        mockMvc.perform(get("/api/employees/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Alex"))
                .andExpect(jsonPath("$.surname").value("Pozdnyakov"))
                .andExpect(jsonPath("$.department").value("IT"))
                .andExpect(jsonPath("$.salary").value(500));
    }

    @Test
    void addNewEmployee() throws Exception {
        Employee employee = new Employee(1, "Alex", "Pozdnyakov", "IT", 500);
        String workerJson = mapper.writeValueAsString(employee);
        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(workerJson))
                .andExpect(status().isOk());
    }

    @Test
    void updateEmployee() throws Exception {
        Employee employee = new Employee(1, "Alex", "Pozdnyakov", "IT", 500);
        String workerJson = mapper.writeValueAsString(employee);
        mockMvc.perform(put("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(workerJson))
                .andExpect(status().isOk());
    }

    @Test
    void deleteEmployees() throws Exception {
        Employee employee = new Employee(1, "Alex", "Pozdnyakov", "IT", 500);
        String workerJson = mapper.writeValueAsString(employee);
        mockMvc.perform(delete("/api/employees/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(workerJson))
                .andExpect(status().isOk());
    }
}


