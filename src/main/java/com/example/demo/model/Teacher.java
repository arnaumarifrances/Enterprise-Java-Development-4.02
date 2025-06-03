package com.example.demo.model;

public class Teacher {
    // Static counter to generate unique teacher IDs
    private static int idCounter = 0;

    // Private attributes
    private final String teacherId;
    private String name;
    private double salary;

    // Constructor that initializes name and salary, and auto-generates ID
    public Teacher(String name, double salary) {
        this.teacherId = "T" + (++idCounter);
        this.setName(name);
        this.setSalary(salary);
    }

    // Getters
    public String getTeacherId() {
        return teacherId;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    // Setter for name with validation
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    // Setter for salary with validation
    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative.");
        }
        this.salary = salary;
    }

    // Optional: toString method for easy printing
    @Override
    public String toString() {
        return "Teacher ID: " + teacherId + ", Name: " + name + ", Salary: " + salary;
    }
}
