package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String courseId;

    private String name;

    private double price;

    private double moneyEarned;

    @ManyToOne
    private Teacher teacher;

    @PrePersist
    private void generateCourseId() {
        if (courseId == null || courseId.isEmpty()) {
            this.courseId = "C-" + UUID.randomUUID();
        }
    }

    public Course(String name, double price) {
        this.setName(name);
        this.setPrice(price);
        this.moneyEarned = 0.0;
    }

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

    public void addEarnings(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Earnings cannot be negative.");
        }
        this.moneyEarned += amount;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", moneyEarned=" + moneyEarned +
                ", teacher=" + (teacher != null ? teacher.getName() : "None") +
                '}';
    }
}
