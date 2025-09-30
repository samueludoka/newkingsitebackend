package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthChecksController {
    @GetMapping("/health")
    public String healthCheck() {
        return "Your Api Is Up and Running.......";
    }
    
}



