package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String teacherId;

    private String name;

    private double salary;

    @PrePersist
    private void generateTeacherId() {
        if (teacherId == null || teacherId.isEmpty()) {
            this.teacherId = "T-" + UUID.randomUUID();
        }
    }

    public Teacher(String name, double salary) {
        this.setName(name);
        this.setSalary(salary);
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative.");
        }
        this.salary = salary;
    }
}
