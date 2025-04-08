package com.example.webDproject;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.Base64;

@Component
public class Encryption {

    // Hash password using SHA-256 (no salt)
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // Compare input password to stored hash
    public static boolean verifyPassword(String inputPassword, String storedHash) {
        String inputHash = hashPassword(inputPassword);
        return inputHash.equals(storedHash);
    }

    // Demo
    public static void main(String[] args) {
        String password = "myPassword123";

        // Signup
        String hashedPassword = hashPassword(password);
        System.out.println("Hashed Password: " + hashedPassword);

        // Login
        boolean isMatch = verifyPassword("myPassword123", hashedPassword);
        System.out.println("Password Match: " + isMatch);
    }
}
