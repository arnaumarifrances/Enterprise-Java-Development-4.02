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
            System.out.println("\nPlease choose an option:");
            System.out.println("1 - View existing data");
            System.out.println("2 - Add new teacher");
            System.out.println("3 - Add new course");
            System.out.println("4 - Add new student");
            System.out.println("5 - Enter command mode");
            System.out.println("0 - Exit");

            System.out.print("> ");
            String option = sc.nextLine().trim();

            switch (option) {
                case "1":
                    viewAllData();
                    break;
                case "2":
                    addTeachers(sc);
                    break;
                case "3":
                    addCourses(sc);
                    break;
                case "4":
                    addStudents(sc);
                    break;
                case "5":
                    commandLoop(sc);
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void viewAllData() {
        System.out.println("\n=== Teachers ===");
        service.getAllTeachers().forEach(System.out::println);

        System.out.println("\n=== Courses ===");
        service.getAllCourses().forEach(System.out::println);

        System.out.println("\n=== Students ===");
        service.getAllStudents().forEach(System.out::println);
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

    private void commandLoop(Scanner sc) {
        System.out.println("Enter commands (ENROLL, ASSIGN, SHOW, LOOKUP, EXIT):");

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
                        System.out.println("Exiting command mode.");
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
