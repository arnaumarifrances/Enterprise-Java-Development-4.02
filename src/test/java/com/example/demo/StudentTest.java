package com.example.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    void testStudentCreation() {
        Student student = new Student("Bob", "Calle Falsa", "bob@mail.com");
        assertEquals("Bob", student.getName());
        assertEquals("Calle Falsa", student.getAddress());
        assertEquals("bob@mail.com", student.getEmail());
    }

    @Test
    void testToStringWhenNotEnrolled() {
        Student student = new Student("Bob", "Address", "mail");
        String result = student.toString();
        assertTrue(result.contains("Not Enrolled"));
    }
}
