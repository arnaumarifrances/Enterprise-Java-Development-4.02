package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.service.SchoolService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SchoolController.class)
class SchoolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SchoolService schoolService;

    @Test
    void getAllCourses() throws Exception {
        Course course = new Course("Java", 200);
        course.setCourseId("C-123");

        when(schoolService.getAllCourses()).thenReturn(List.of(course));

        mockMvc.perform(get("/api/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Java"));
    }

    @Test
    void getCourseById_found() throws Exception {
        Course course = new Course("Python", 150);
        course.setCourseId("C-456");

        when(schoolService.getCourseById("C-456")).thenReturn(course);

        mockMvc.perform(get("/api/courses/C-456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Python"));
    }

    @Test
    void getCourseById_notFound() throws Exception {
        when(schoolService.getCourseById("C-999")).thenReturn(null);

        mockMvc.perform(get("/api/courses/C-999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllStudents() throws Exception {
        Student student = new Student("Alice", "Calle 123", "alice@mail.com");
        student.setStudentId("S-1");

        when(schoolService.getAllStudents()).thenReturn(List.of(student));

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alice"));
    }

    @Test
    void getStudentById_found() throws Exception {
        Student student = new Student("Bob", "Calle 456", "bob@mail.com");
        student.setStudentId("S-2");

        when(schoolService.getStudentById("S-2")).thenReturn(student);

        mockMvc.perform(get("/api/students/S-2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("bob@mail.com"));
    }

    @Test
    void getStudentById_notFound() throws Exception {
        when(schoolService.getStudentById("S-999")).thenReturn(null);

        mockMvc.perform(get("/api/students/S-999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllTeachers() throws Exception {
        Teacher teacher = new Teacher("Juan", 1000);
        teacher.setTeacherId("T-1");

        when(schoolService.getAllTeachers()).thenReturn(List.of(teacher));

        mockMvc.perform(get("/api/teachers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Juan"));
    }

    @Test
    void getTeacherById_found() throws Exception {
        Teacher teacher = new Teacher("Maria", 1200);
        teacher.setTeacherId("T-2");

        when(schoolService.getTeacherById("T-2")).thenReturn(teacher);

        mockMvc.perform(get("/api/teachers/T-2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salary").value(1200));
    }

    @Test
    void getTeacherById_notFound() throws Exception {
        when(schoolService.getTeacherById("T-999")).thenReturn(null);

        mockMvc.perform(get("/api/teachers/T-999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getProfit() throws Exception {
        when(schoolService.calculateProfit()).thenReturn(500.0);

        mockMvc.perform(get("/api/profit"))
                .andExpect(status().isOk())
                .andExpect(content().string("500.0"));
    }

    @Test
    void enrollStudent_success() throws Exception {
        doNothing().when(schoolService).enrollStudent("S-1", "C-1");

        mockMvc.perform(post("/api/enroll/S-1/C-1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Student enrolled successfully."));
    }

    @Test
    void enrollStudent_failure() throws Exception {
        doThrow(new IllegalArgumentException("Student not found"))
                .when(schoolService).enrollStudent("S-X", "C-X");

        mockMvc.perform(post("/api/enroll/S-X/C-X"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Student not found")));
    }

    @Test
    void assignTeacher_success() throws Exception {
        doNothing().when(schoolService).assignTeacher("T-1", "C-1");

        mockMvc.perform(post("/api/assign/T-1/C-1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Teacher assigned successfully."));
    }

    @Test
    void assignTeacher_failure() throws Exception {
        doThrow(new IllegalArgumentException("Course not found"))
                .when(schoolService).assignTeacher("T-99", "C-99");

        mockMvc.perform(post("/api/assign/T-99/C-99"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Course not found")));
    }
}
