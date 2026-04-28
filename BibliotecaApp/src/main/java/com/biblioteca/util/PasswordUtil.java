package com.biblioteca.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {
    // Code smell: Hacer un constructor privado


    public static String hashPassword(String password) {
        try {
            // Security hotspot
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Code smell
            return null;
        }
    }

    // Code smell
    // No se usa
    private static boolean validarFormatoEmail(String email) {
        return email.contains("@");
    }
}
