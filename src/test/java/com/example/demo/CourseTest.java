package com.example.demo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    @Test
    void testValidCourseCreation() {
        Course course = new Course("Java", 100);
        assertEquals("Java", course.getName());
        assertEquals(100, course.getPrice());
        assertEquals(0, course.getMoneyEarned());
    }

    @Test
    void testSetInvalidName() {
        Course course = new Course();
        assertThrows(IllegalArgumentException.class, () -> course.setName(""));
    }

    @Test
    void testSetNegativePrice() {
        Course course = new Course();
        assertThrows(IllegalArgumentException.class, () -> course.setPrice(-5));
    }

    @Test
    void testAddEarnings() {
        Course course = new Course("Java", 50);
        course.addEarnings(100);
        assertEquals(100, course.getMoneyEarned());
    }

    @Test
    void testAddNegativeEarnings() {
        Course course = new Course();
        assertThrows(IllegalArgumentException.class, () -> course.addEarnings(-10));
    }
}
