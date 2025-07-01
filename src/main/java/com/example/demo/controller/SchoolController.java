package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/enroll/{studentId}/{courseId}")
    public ResponseEntity<String> enrollStudent(@PathVariable String studentId,
                                                @PathVariable String courseId) {
        try {
            schoolService.enrollStudent(studentId, courseId);
            return ResponseEntity.ok("Student enrolled successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Enrollment failed: " + e.getMessage());
        }
    }

    @PostMapping("/assign/{teacherId}/{courseId}")
    public ResponseEntity<String> assignTeacher(@PathVariable String teacherId,
                                                @PathVariable String courseId) {
        try {
            schoolService.assignTeacher(teacherId, courseId);
            return ResponseEntity.ok("Teacher assigned successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Assignment failed: " + e.getMessage());
        }
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(schoolService.getAllCourses());
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable String courseId) {
        Course course = schoolService.getCourseById(courseId);
        return (course != null) ? ResponseEntity.ok(course) : ResponseEntity.notFound().build();
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(schoolService.getAllStudents());
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable String studentId) {
        Student student = schoolService.getStudentById(studentId);
        return (student != null) ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(schoolService.getAllTeachers());
    }

    @GetMapping("/teachers/{teacherId}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable String teacherId) {
        Teacher teacher = schoolService.getTeacherById(teacherId);
        return (teacher != null) ? ResponseEntity.ok(teacher) : ResponseEntity.notFound().build();
    }

    @GetMapping("/profit")
    public ResponseEntity<Double> getProfit() {
        return ResponseEntity.ok(schoolService.calculateProfit());
    }
}
