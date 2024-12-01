/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author ifunanya n01646428
 */
public class InputValidator {
// Validate that the GPA is within the range 0.0 to 4.0
    public static boolean validateGPA(double gpa) {
        return gpa >= 0.0 && gpa <= 4.0;
    }

    // Validate the student ID format (e.g., S-1234)
    public static boolean validateId(String id) {
        return id.matches("S-\\d{4}");  // Match pattern S-XXXX
    }

    // Validate that a string is not empty or blank
    public static boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }
}



    

