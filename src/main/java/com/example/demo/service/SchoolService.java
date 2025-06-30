package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.model.Course;
import com.example.demo.model.Teacher;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;
    private final TeacherRepository teacherRepo;

    public SchoolService(StudentRepository studentRepo, CourseRepository courseRepo, TeacherRepository teacherRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.teacherRepo = teacherRepo;
    }

    // ENROLL [STUDENT_ID] [COURSE_ID]
    public void enrollStudent(String studentId, String courseId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + studentId));
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found: " + courseId));

        if (student.getCourse() != null) {
            throw new IllegalStateException("Student is already enrolled in a course.");
        }

        student.setCourse(course);
        course.setMoneyEarned(course.getMoneyEarned() + course.getPrice());

        studentRepo.save(student);
        courseRepo.save(course);
    }

    // ASSIGN [TEACHER_ID] [COURSE_ID]
    public void assignTeacher(String teacherId, String courseId) {
        Teacher teacher = teacherRepo.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found: " + teacherId));
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found: " + courseId));

        course.setTeacher(teacher);
        courseRepo.save(course);
    }

    // SHOW COURSES
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    // LOOKUP COURSE [COURSE_ID]
    public Course getCourseById(String courseId) {
        return courseRepo.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found: " + courseId));
    }

    // SHOW STUDENTS
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    // LOOKUP STUDENT [STUDENT_ID]
    public Student getStudentById(String studentId) {
        return studentRepo.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + studentId));
    }

    // SHOW TEACHERS
    public List<Teacher> getAllTeachers() {
        return teacherRepo.findAll();
    }

    // LOOKUP TEACHER [TEACHER_ID]
    public Teacher getTeacherById(String teacherId) {
        return teacherRepo.findById(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found: " + teacherId));
    }

    // SHOW PROFIT
    public double getProfit() {
        double totalEarned = courseRepo.findAll().stream()
                .mapToDouble(Course::getMoneyEarned)
                .sum();

        double totalSalaries = teacherRepo.findAll().stream()
                .mapToDouble(Teacher::getSalary)
                .sum();

        return totalEarned - totalSalaries;
    }
}
