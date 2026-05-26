package com.olp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olp.dto.request.LoginRequest;

@RestController
@RequestMapping("/instructor")
public class InstructorController {
    @GetMapping("/test")
    public String testMehtod(@RequestParam LoginRequest param) {
        return "Success!";
    }

    @GetMapping("/test")
    public String instructor() {
        return "Instructor Api";
    }

}
