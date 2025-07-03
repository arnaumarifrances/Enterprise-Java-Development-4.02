package com.example.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TeacherTest {

    @Test
    void testValidTeacherCreation() {
        Teacher teacher = new Teacher("Alice", 3000);
        assertEquals("Alice", teacher.getName());
        assertEquals(3000, teacher.getSalary());
    }

    @Test
    void testInvalidName() {
        Teacher teacher = new Teacher();
        assertThrows(IllegalArgumentException.class, () -> teacher.setName("  "));
    }

    @Test
    void testInvalidSalary() {
        Teacher teacher = new Teacher();
        assertThrows(IllegalArgumentException.class, () -> teacher.setSalary(-100));
    }
}
