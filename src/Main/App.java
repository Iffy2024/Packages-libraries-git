/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Model.Student;
import Util.InputValidator;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;  
import java.util.Scanner; 

/**
 *
 * @author ifunanya n01646428
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        ArrayList<Student> students = new ArrayList<>();

        while (true) {
            // Display menu for user actions
            System.out.println("\nStudent Management System");
            System.out.println("1. Add a new student");
            System.out.println("2. Display all students");
            System.out.println("3. Search for a student by ID");
            System.out.println("4. Update a student's GPA");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = getValidChoice(scanner); 

            switch (choice) {
                case 1:
                    addStudent(scanner, students);
                    break;
                case 2:
                    displayStudents(students);
                    break;
                case 3:
                    searchStudentById(scanner, students);
                    break;
                case 4:
                    updateGPA(scanner, students);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    
    private static int getValidChoice(Scanner scanner) {
        int choice;
        while (true) {
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid choice. Please enter a valid number between 1 and 5.");
                scanner.nextLine();
                continue;
            }
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice >= 1 && choice <= 5) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid number between 1 and 5.");
            }
        }
        return choice;
    }

    private static void addStudent(Scanner scanner, ArrayList<Student> students) {
        System.out.print("Enter student's ID (e.g., S-1234): ");
        String id = scanner.nextLine();
        if (!InputValidator.validateId(id)) {
            System.out.println("Invalid ID format. Please use the format S-XXXX.");
            return;
        }

        System.out.print("Enter student's first name: ");
        String firstName = getValidStringInput(scanner);
        System.out.print("Enter student's last name: ");
        String lastName = getValidStringInput(scanner);
        System.out.print("Enter student's GPA: ");
        double gpa = getValidGPA(scanner);

        // Reverse the first name using StringUtils
        String reversedFirstName = StringUtils.reverse(firstName);

        Student newStudent = new Student(id, reversedFirstName, lastName, gpa);
        students.add(newStudent);  // Add student to list
        System.out.println("Student added: " + newStudent);
    }

    private static void displayStudents(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.forEach(System.out::println);  // Print each student's details
        }
    }

    private static void searchStudentById(Scanner scanner, ArrayList<Student> students) {
        System.out.print("Enter student's ID (e.g., S-1234): ");
        String id = scanner.nextLine();

        boolean found = false;
        for (Student student : students) {
            if (student.getId().equals(id)) {
                System.out.println("Student found: " + student);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No student found with ID: " + id);
        }
    }

    private static void updateGPA(Scanner scanner, ArrayList<Student> students) {
        System.out.print("Enter student's ID to update GPA: ");
        String id = scanner.nextLine();

        Student studentToUpdate = null;
        for (Student student : students) {
            if (student.getId().equals(id)) {
                studentToUpdate = student;
                break;
            }
        }

        if (studentToUpdate == null) {
            System.out.println("No student found with ID: " + id);
            return;
        }

        System.out.print("Enter new GPA: ");
        double newGpa = getValidGPA(scanner);
        studentToUpdate.setGpa(newGpa);  // Updates GPA
        System.out.println("GPA updated: " + studentToUpdate);
    }

    private static String getValidStringInput(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.nextLine();
            if (InputValidator.isNotBlank(input)) {
                break;
            } else {
                System.out.print("Invalid input. Please enter a non-empty value: ");
            }
        }
        return input;
    }

    // Method to get a valid GPA (within range)
    private static double getValidGPA(Scanner scanner) {
        double gpa = -1;
        while (gpa < 0 || gpa > 4) {
            if (!scanner.hasNextDouble()) {
                System.out.print("Invalid GPA. Please enter a value between 0.0 and 4.0: ");
                scanner.nextLine();
                continue;
            }
            gpa = scanner.nextDouble();
            if (gpa < 0 || gpa > 4) {
                System.out.print("Invalid GPA. Please enter a value between 0.0 and 4.0: ");
            }
        }
        scanner.nextLine(); 
        return gpa;
    }
}


   