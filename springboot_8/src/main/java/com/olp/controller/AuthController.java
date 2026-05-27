package com.olp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olp.dto.request.LoginRequest;
import com.olp.dto.request.RegisterReqInstructor;
import com.olp.dto.request.RegisterRequest;
import com.olp.dto.response.AuthResponse;
import com.olp.entity.Instructor;
import com.olp.entity.Student;
import com.olp.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthController {

    private final AuthService authService;

    @PostMapping("/registerStudent")
    public Student registerAsStudent(@RequestBody RegisterRequest request) {
        return authService.registerStudent(request);
    }

    @PostMapping("/registerInstructor")
    public Instructor registerAsInstructor(@RequestBody RegisterReqInstructor request) {
        return authService.registerInstructor(request);
    }

    @PostMapping("/loginStudent")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/loginInstructor")
    public AuthResponse loginInstructor(@RequestBody LoginRequest request) {
        return authService.loginAsInstructor(request);
    }

}