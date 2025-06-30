package com.example.demo.model;

public class Course {
    // Static counter to generate unique course IDs
    private static int idCounter = 0;

    // Private attributes
    private final String courseId;
    private String name;
    private double price;
    private double moneyEarned;
    private Teacher teacher; // Nullable

    // Constructor that initializes name and price, and auto-generates ID
    public Course(String name, double price) {
        this.courseId = "C" + (++idCounter);
        this.setName(name);
        this.setPrice(price);
        this.moneyEarned = 0.0;
        this.teacher = null;
    }

    // Getters
    public String getCourseId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getMoneyEarned() {
        return moneyEarned;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    // Setters with validation
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty.");
        }
        this.name = name;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Course price cannot be negative.");
        }
        this.price = price;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    // Adds income to the course when a student enrolls
    public void addEarnings(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Earnings cannot be negative.");
        }
        this.moneyEarned += amount;
    }

    // Optional: toString method for easy printing
    @Override
    public String toString() {
        return "Course ID: " + courseId +
                ", Name: " + name +
                ", Price: " + price +
                ", Money Earned: " + moneyEarned +
                ", Teacher: " + (teacher != null ? teacher.getName() : "None");
    }
}
