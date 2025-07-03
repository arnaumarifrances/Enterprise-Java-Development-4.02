package com.example.demo;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.SchoolService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SchoolServiceTest {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private TeacherRepository teacherRepository;
    private SchoolService schoolService;

    @BeforeEach
    void setUp() {
        studentRepository = mock(StudentRepository.class);
        courseRepository = mock(CourseRepository.class);
        teacherRepository = mock(TeacherRepository.class);
        schoolService = new SchoolService(studentRepository, courseRepository, teacherRepository);
    }

    @Test
    void testEnrollStudent_Success() {
        Course course = new Course("Java", 200.0);
        course.setCourseId("C-001");
        Student student = new Student("Luis", "Calle", "luis@mail.com");
        student.setStudentId("S-001");

        when(courseRepository.findByCourseId("C-001")).thenReturn(Optional.of(course));
        when(studentRepository.findByStudentId("S-001")).thenReturn(Optional.of(student));

        schoolService.enrollStudent("S-001", "C-001");

        assertEquals(course, student.getCourse());
        assertEquals(200.0, course.getMoneyEarned());
        verify(studentRepository).save(student);
        verify(courseRepository).save(course);
    }

    @Test
    void testEnrollStudent_AlreadyEnrolled() {
        Course course = new Course("Math", 100.0);
        course.setCourseId("C-123");

        Student student = new Student("Ana", "Calle", "ana@mail.com");
        student.setStudentId("S-123");
        student.setCourse(course); // ya está matriculado

        when(courseRepository.findByCourseId("C-123")).thenReturn(Optional.of(course));
        when(studentRepository.findByStudentId("S-123")).thenReturn(Optional.of(student));

        assertThrows(IllegalStateException.class, () -> {
            schoolService.enrollStudent("S-123", "C-123");
        });
    }

    @Test
    void testAssignTeacher_Success() {
        Teacher teacher = new Teacher("Juan", 1000.0);
        teacher.setTeacherId("T-001");

        Course course = new Course("SQL", 150.0);
        course.setCourseId("C-002");

        when(teacherRepository.findByTeacherId("T-001")).thenReturn(Optional.of(teacher));
        when(courseRepository.findByCourseId("C-002")).thenReturn(Optional.of(course));

        schoolService.assignTeacher("T-001", "C-002");

        assertEquals(teacher, course.getTeacher());
        verify(courseRepository).save(course);
    }

    @Test
    void testCalculateProfit() {
        Course course1 = new Course("Course1", 100.0);
        course1.addEarnings(300.0);
        Course course2 = new Course("Course2", 200.0);
        course2.addEarnings(200.0);

        Teacher teacher1 = new Teacher("Pedro", 250.0);
        Teacher teacher2 = new Teacher("Maria", 150.0);

        when(courseRepository.findAll()).thenReturn(List.of(course1, course2));
        when(teacherRepository.findAll()).thenReturn(List.of(teacher1, teacher2));

        double profit = schoolService.calculateProfit();
        assertEquals(100.0, profit); // 500 - 400
    }
}
