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

    // ENROLL [STUDENT_ID] [COURSE_ID]
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

    // ASSIGN [TEACHER_ID] [COURSE_ID]
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

    // SHOW COURSES
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(schoolService.getAllCourses());
    }

    // LOOKUP COURSE [COURSE_ID]
    @GetMapping("/courses/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable String courseId) {
        Course course = schoolService.getCourseById(courseId);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // SHOW STUDENTS
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(schoolService.getAllStudents());
    }

    // LOOKUP STUDENT [STUDENT_ID]
    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable String studentId) {
        Student student = schoolService.getStudentById(studentId);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // SHOW TEACHERS
    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(schoolService.getAllTeachers());
    }

    // LOOKUP TEACHER [TEACHER_ID]
    @GetMapping("/teachers/{teacherId}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable String teacherId) {
        Teacher teacher = schoolService.getTeacherById(teacherId);
        if (teacher != null) {
            return ResponseEntity.ok(teacher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // SHOW PROFIT
    @GetMapping("/profit")
    public ResponseEntity<Double> getProfit() {
        double profit = schoolService.calculateProfit();
        return ResponseEntity.ok(profit);
    }
}
