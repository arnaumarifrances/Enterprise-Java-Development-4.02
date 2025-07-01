package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String studentId;

    private String name;

    private String address;

    private String email;

    @ManyToOne
    private Course course;

    @PrePersist
    private void generateStudentId() {
        if (studentId == null || studentId.isEmpty()) {
            this.studentId = "S-" + UUID.randomUUID();
        }
    }

    public Student(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", course=" + (course != null ? course.getName() : "Not Enrolled") +
                '}';
    }
}
