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

        // Paso 1: nombre de la escuela
        System.out.print("Enter the name of the school: ");
        String schoolName = sc.nextLine();
        System.out.println("School: " + schoolName);

        // Paso 2: crear profesores
        System.out.print("How many teachers to create? ");
        int numTeachers = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < numTeachers; i++) {
            System.out.println("Enter teacher " + (i + 1) + " name: ");
            String name = sc.nextLine();
            System.out.println("Enter salary: ");
            double salary = Double.parseDouble(sc.nextLine());
            service.saveTeacher(new Teacher(name, salary));
        }

        // Paso 3: crear cursos
        System.out.print("How many courses to create? ");
        int numCourses = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < numCourses; i++) {
            System.out.println("Enter course " + (i + 1) + " name: ");
            String name = sc.nextLine();
            System.out.println("Enter price: ");
            double price = Double.parseDouble(sc.nextLine());
            service.saveCourse(new Course(name, price));
        }

        // Paso 4: crear estudiantes
        System.out.print("How many students to create? ");
        int numStudents = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter student " + (i + 1) + " name: ");
            String name = sc.nextLine();
            System.out.println("Enter address: ");
            String address = sc.nextLine();
            System.out.println("Enter email: ");
            String email = sc.nextLine();
            service.saveStudent(new Student(name, address, email));
        }

        // Paso 5: bucle de comandos
        System.out.println("You can now enter commands.");
        while (true) {
            System.out.print("> ");
            String input = sc.nextLine().trim();
            String[] tokens = input.split("\\s+");
            if (tokens.length == 0) continue;

            try {
                switch (tokens[0].toUpperCase()) {
                    case "ENROLL":
                        service.enrollStudent(tokens[1], tokens[2]);
                        System.out.println("Student enrolled.");
                        break;
                    case "ASSIGN":
                        service.assignTeacher(tokens[1], tokens[2]);
                        System.out.println("Teacher assigned.");
                        break;
                    case "SHOW":
                        switch (tokens[1].toUpperCase()) {
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
                                System.out.println("Profit: " + service.calculateProfit());
                                break;
                            default:
                                System.out.println("Unknown SHOW command.");
                        }
                        break;
                    case "LOOKUP":
                        switch (tokens[1].toUpperCase()) {
                            case "STUDENT":
                                System.out.println(service.getStudentById(tokens[2]));
                                break;
                            case "COURSE":
                                System.out.println(service.getCourseById(tokens[2]));
                                break;
                            case "TEACHER":
                                System.out.println(service.getTeacherById(tokens[2]));
                                break;
                            default:
                                System.out.println("Unknown LOOKUP command.");
                        }
                        break;
                    case "EXIT":
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Unknown command.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
