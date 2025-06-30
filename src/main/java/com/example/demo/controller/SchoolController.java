package com.example.demo.controller;

import com.example.school.model.Course;
import com.example.school.model.Student;
import com.example.school.model.Teacher;
import com.example.demo.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    // ENROLL [STUDENT_ID] [COURSE_ID]
    @PostMapping("/enroll/{studentId}/{courseId}")
    public ResponseEntity<String> enrollStudent(@PathVariable String studentId, @PathVariable String courseId) {
        try {
            schoolService.enrollStudent(studentId, courseId);
            return ResponseEntity.ok("Student enrolled successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // ASSIGN [TEACHER_ID] [COURSE_ID]
    @PostMapping("/assign/{teacherId}/{courseId}")
    public ResponseEntity<String> assignTeacher(@PathVariable String teacherId, @PathVariable String courseId) {
        try {
            schoolService.assignTeacher(teacherId, courseId);
            return ResponseEntity.ok("Teacher assigned successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // SHOW COURSES
    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return schoolService.getAllCourses();
    }

    // LOOKUP COURSE [COURSE_ID]
    @GetMapping("/courses/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable String courseId) {
        return schoolService.getCourseById(courseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // SHOW STUDENTS
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return schoolService.getAllStudents();
    }

    // LOOKUP STUDENT [STUDENT_ID]
    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable String studentId) {
        return schoolService.getStudentById(studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // SHOW TEACHERS
    @GetMapping("/teachers")
    public List<Teacher> getAllTeachers() {
        return schoolService.getAllTeachers();
    }

    // LOOKUP TEACHER [TEACHER_ID]
    @GetMapping("/teachers/{teacherId}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable String teacherId) {
        return schoolService.getTeacherById(teacherId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // SHOW PROFIT
    @GetMapping("/profit")
    public ResponseEntity<Double> getProfit() {
        double profit = schoolService.calculateProfit();
        return ResponseEntity.ok(profit);
    }
}
