package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;
    private final TeacherRepository teacherRepo;

    public SchoolService(StudentRepository studentRepo,
                         CourseRepository courseRepo,
                         TeacherRepository teacherRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.teacherRepo = teacherRepo;
    }

    public void enrollStudent(String studentId, String courseId) {
        Student student = studentRepo.findByStudentId(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Course course = courseRepo.findByCourseId(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        if (student.getCourse() != null) {
            throw new IllegalStateException("Student already enrolled");
        }

        student.setCourse(course);
        course.addEarnings(course.getPrice());

        studentRepo.save(student);
        courseRepo.save(course);
    }

    public void assignTeacher(String teacherId, String courseId) {
        Teacher teacher = teacherRepo.findByTeacherId(teacherId)
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));

        Course course = courseRepo.findByCourseId(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        course.setTeacher(teacher);
        courseRepo.save(course);
    }

    public double calculateProfit() {
        double totalEarnings = courseRepo.findAll()
                .stream()
                .mapToDouble(Course::getMoneyEarned)
                .sum();

        double totalSalaries = teacherRepo.findAll()
                .stream()
                .mapToDouble(Teacher::getSalary)
                .sum();

        return totalEarnings - totalSalaries;
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public Course getCourseById(String courseId) {
        return courseRepo.findByCourseId(courseId).orElse(null);
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Student getStudentById(String studentId) {
        return studentRepo.findByStudentId(studentId).orElse(null);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepo.findAll();
    }

    public Teacher getTeacherById(String teacherId) {
        return teacherRepo.findByTeacherId(teacherId).orElse(null);
    }

    // === NUEVOS MÉTODOS ===

    public void saveStudent(Student student) {
        studentRepo.save(student);
    }

    public void saveCourse(Course course) {
        courseRepo.save(course);
    }

    public void saveTeacher(Teacher teacher) {
        teacherRepo.save(teacher);
    }
}
