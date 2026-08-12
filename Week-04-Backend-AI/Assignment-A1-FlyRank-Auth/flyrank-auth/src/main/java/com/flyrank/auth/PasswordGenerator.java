package com.flyrank.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String password = "12345";

        String hash = encoder.encode(password);

        System.out.println("BCrypt Hash:");
        System.out.println(hash);
    }
}