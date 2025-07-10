package com.example.demo.DataInitializer;

import com.example.demo.model.Employee;
import com.example.demo.model.Patient;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void run(String... args) throws Exception {
        // Insertar empleados
        employeeRepository.save(new Employee("Alonso Flores", "cardiology", Employee.Status.ON_CALL));
        employeeRepository.save(new Employee("Sam Ortega", "immunology", Employee.Status.ON));
        employeeRepository.save(new Employee("German Ruiz", "cardiology", Employee.Status.OFF));
        employeeRepository.save(new Employee("Maria Lin", "pulmonary", Employee.Status.ON));
        employeeRepository.save(new Employee("Paolo Rodriguez", "orthopaedic", Employee.Status.ON_CALL));
        employeeRepository.save(new Employee("John Paul Armes", "psychiatric", Employee.Status.OFF));

        // Insertar pacientes
        patientRepository.save(new Patient("Jaime Jordan", LocalDate.of(1984, 3, 2), employeeRepository.findById(564134L).orElse(null)));
        patientRepository.save(new Patient("Marian Garcia", LocalDate.of(1972, 1, 12), employeeRepository.findById(564134L).orElse(null)));
        patientRepository.save(new Patient("Julia Dusterdieck", LocalDate.of(1954, 6, 11), employeeRepository.findById(356712L).orElse(null)));
        patientRepository.save(new Patient("Steve McDuck", LocalDate.of(1931, 11, 10), employeeRepository.findById(761527L).orElse(null)));
        patientRepository.save(new Patient("Marian Garcia", LocalDate.of(1999, 2, 15), employeeRepository.findById(172456L).orElse(null)));
    }
}
