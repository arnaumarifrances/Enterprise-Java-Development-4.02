package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.Patient;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee doctor1;
    private Employee doctor2;
    private Patient patient1;
    private Patient patient2;

    @BeforeEach
    void setUp() {
        patientRepository.deleteAll();
        employeeRepository.deleteAll();

        doctor1 = new Employee("Alonso Flores", "cardiology", Employee.Status.ON_CALL);
        doctor2 = new Employee("Maria Lin", "pulmonary", Employee.Status.ON);

        employeeRepository.saveAll(List.of(doctor1, doctor2));

        patient1 = new Patient("Jaime Jordan", LocalDate.of(1984, 3, 2), doctor1);
        patient2 = new Patient("Marian Garcia", LocalDate.of(1972, 1, 12), doctor2);

        patientRepository.saveAll(List.of(patient1, patient2));
    }

    @Test
    void testGetAllPatients() throws Exception {
        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[?(@.name == 'Jaime Jordan')]").exists())
                .andExpect(jsonPath("$[?(@.name == 'Marian Garcia')]").exists());
    }

    @Test
    void testGetPatientById() throws Exception {
        mockMvc.perform(get("/api/patients/{id}", patient1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jaime Jordan"))
                .andExpect(jsonPath("$.admittedBy.name").value("Alonso Flores"));
    }

    @Test
    void testGetPatientByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/patients/{id}", 9999L))
                .andExpect(status().isOk())
                .andExpect(content().string(""));  // devuelve null -> cuerpo vacío
    }

    @Test
    void testGetPatientsByDateOfBirth() throws Exception {
        mockMvc.perform(get("/api/patients/dob")
                        .param("start", "1970-01-01")
                        .param("end", "1990-12-31"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", anyOf(is("Jaime Jordan"), is("Marian Garcia"))));
    }

    @Test
    void testGetPatientsByDoctorDepartment() throws Exception {
        mockMvc.perform(get("/api/patients/admitted-by-department/{department}", "cardiology"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].admittedBy.department").value("cardiology"))
                .andExpect(jsonPath("$[0].name").value("Jaime Jordan"));
    }

    @Test
    void testGetPatientsByDoctorStatus() throws Exception {
        mockMvc.perform(get("/api/patients/doctor-status/{status}", "ON"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].admittedBy.status").value("ON"))
                .andExpect(jsonPath("$[0].name").value("Marian Garcia"));
    }
}
