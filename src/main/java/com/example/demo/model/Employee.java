package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Esto permite que Hibernate genere el ID automáticamente
    private Long id;

    private String name;
    private String department;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Employee() {}

    public Employee(String name, String department, Status status) {
        this.name = name;
        this.department = department;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        ON_CALL,
        ON,
        OFF
    }
}
