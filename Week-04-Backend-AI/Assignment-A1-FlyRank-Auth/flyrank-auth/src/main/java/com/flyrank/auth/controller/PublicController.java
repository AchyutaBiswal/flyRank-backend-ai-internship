package com.flyrank.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PublicController {

    // Public endpoint
    @GetMapping("/public/info")
    public Map<String, String> publicInfo() {

        return Map.of(
                "message",
                "Welcome stranger! This info is public."
        );
    }

    // Protected endpoint
    @GetMapping("/private/info")
    public Map<String, String> privateInfo() {

        return Map.of(
                "message",
                "Welcome authenticated user! This information is private."
        );
    }
}