package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee emp1;
    private Employee emp2;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();

        emp1 = new Employee("Alonso Flores", "cardiology", Employee.Status.ON_CALL);
        emp2 = new Employee("Maria Lin", "pulmonary", Employee.Status.ON);

        employeeRepository.saveAll(List.of(emp1, emp2));
    }

    @Test
    void testGetAllEmployees() throws Exception {
        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[?(@.name == 'Alonso Flores')]").exists())
                .andExpect(jsonPath("$[?(@.name == 'Maria Lin')]").exists());
    }

    @Test
    void testGetEmployeeById() throws Exception {
        mockMvc.perform(get("/api/employees/{id}", emp1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alonso Flores"))
                .andExpect(jsonPath("$.department").value("cardiology"))
                .andExpect(jsonPath("$.status").value("ON_CALL"));
    }

    @Test
    void testGetEmployeeByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/employees/{id}", 9999L))
                .andExpect(status().isOk())  // tu controlador devuelve null, que se traduce en 200 con cuerpo vacío
                .andExpect(content().string("")); // vacio
    }

    @Test
    void testGetEmployeesByStatus() throws Exception {
        mockMvc.perform(get("/api/employees/status/{status}", "ON"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Maria Lin"))
                .andExpect(jsonPath("$[0].status").value("ON"));
    }

    @Test
    void testGetEmployeesByDepartment() throws Exception {
        mockMvc.perform(get("/api/employees/department/{department}", "cardiology"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].department").value("cardiology"))
                .andExpect(jsonPath("$[0].name").value("Alonso Flores"));
    }
}
