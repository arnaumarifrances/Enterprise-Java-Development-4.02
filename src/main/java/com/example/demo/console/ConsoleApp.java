package com.example.demo.console;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleApp implements CommandLineRunner {

    @Autowired
    private SchoolService service;

    @Override
    public void run(String... args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Welcome to IronSchool Console ===");

        while (true) {
            System.out.println("\nMain Menu - Choose a command:");
            System.out.println("ENROLL - Add students, teachers or courses");
            System.out.println("SHOW - View lists of students, teachers, courses or profit");
            System.out.println("ASSIGN - Assign teacher to course or course to student");
            System.out.println("LOOKUP - Search by ID");
            System.out.println("EXIT - Exit program");

            System.out.print("> ");
            String input = sc.nextLine().trim().toUpperCase();

            switch (input) {
                case "ENROLL":
                    handleEnroll(sc);
                    break;
                case "SHOW":
                    handleShow(sc);
                    break;
                case "ASSIGN":
                    handleAssign(sc);
                    break;
                case "LOOKUP":
                    handleLookup(sc);
                    break;
                case "EXIT":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }

    private void handleEnroll(Scanner sc) {
        System.out.println("Enroll what? (STUDENT / TEACHER / COURSE):");
        String type = sc.nextLine().trim().toUpperCase();
        switch (type) {
            case "STUDENT":
                addStudents(sc);
                break;
            case "TEACHER":
                addTeachers(sc);
                break;
            case "COURSE":
                addCourses(sc);
                break;
            default:
                System.out.println("Unknown entity.");
        }
    }

    private void handleShow(Scanner sc) {
        System.out.println("Show what? (STUDENTS / TEACHERS / COURSES / PROFIT):");
        String type = sc.nextLine().trim().toUpperCase();
        switch (type) {
            case "STUDENTS":
                service.getAllStudents().forEach(System.out::println);
                break;
            case "TEACHERS":
                service.getAllTeachers().forEach(System.out::println);
                break;
            case "COURSES":
                service.getAllCourses().forEach(System.out::println);
                break;
            case "PROFIT":
                System.out.println("Total Profit: " + service.calculateProfit());
                break;
            default:
                System.out.println("Unknown show option.");
        }
    }

    private void handleAssign(Scanner sc) {
        System.out.println("Assign what? (TEACHER_TO_COURSE / COURSE_TO_STUDENT):");
        String type = sc.nextLine().trim().toUpperCase();
        switch (type) {
            case "TEACHER_TO_COURSE":
                System.out.print("Teacher ID: ");
                String teacherId = sc.nextLine();
                System.out.print("Course ID: ");
                String courseId = sc.nextLine();
                service.assignTeacher(teacherId, courseId);
                System.out.println("Teacher assigned to course.");
                break;
            case "COURSE_TO_STUDENT":
                System.out.print("Student ID: ");
                String studentId = sc.nextLine();
                System.out.print("Course ID: ");
                String courseId2 = sc.nextLine();
                service.enrollStudent(studentId, courseId2);
                System.out.println("Student enrolled in course.");
                break;
            default:
                System.out.println("Unknown assign option.");
        }
    }

    private void handleLookup(Scanner sc) {
        System.out.println("Lookup what? (STUDENT / TEACHER / COURSE):");
        String type = sc.nextLine().trim().toUpperCase();
        System.out.print("Enter ID: ");
        String id = sc.nextLine();
        switch (type) {
            case "STUDENT":
                System.out.println(service.getStudentById(id));
                break;
            case "TEACHER":
                System.out.println(service.getTeacherById(id));
                break;
            case "COURSE":
                System.out.println(service.getCourseById(id));
                break;
            default:
                System.out.println("Unknown lookup option.");
        }
    }

    private void addStudents(Scanner sc) {
        System.out.print("How many students to create? ");
        int num = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < num; i++) {
            System.out.println("Enter student " + (i + 1) + " name: ");
            String name = sc.nextLine();
            System.out.println("Enter address: ");
            String address = sc.nextLine();
            System.out.println("Enter email: ");
            String email = sc.nextLine();
            service.saveStudent(new Student(name, address, email));
        }
    }

    private void addTeachers(Scanner sc) {
        System.out.print("How many teachers to create? ");
        int num = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < num; i++) {
            System.out.println("Enter teacher " + (i + 1) + " name: ");
            String name = sc.nextLine();
            System.out.println("Enter salary: ");
            double salary = Double.parseDouble(sc.nextLine());
            service.saveTeacher(new Teacher(name, salary));
        }
    }

    private void addCourses(Scanner sc) {
        System.out.print("How many courses to create? ");
        int num = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < num; i++) {
            System.out.println("Enter course " + (i + 1) + " name: ");
            String name = sc.nextLine();
            System.out.println("Enter price: ");
            double price = Double.parseDouble(sc.nextLine());
            service.saveCourse(new Course(name, price));
        }
    }
}
