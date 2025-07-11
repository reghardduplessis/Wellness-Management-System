package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class AuthenticateUtils {

    // Hashes the password using SHA-256
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not available", e);
        }
    }

    // Validates password strength
    public static boolean isStrongPassword(String password) {
        // Minimum 8 characters, at least 1 uppercase, 1 lowercase, 1 number, 1 special character
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+=-]).{8,}$";
        return Pattern.matches(pattern, password);
    }

    // Validates email format
    public static boolean isValidEmail(String email) {
        String pattern = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$";
        return Pattern.matches(pattern, email);
    }

    // Validates phone number (simple check for digits and length)
    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{10,15}");
    }
}