package com.olp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olp.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/test")
    public String studentApi() {
        return "Student APi";
    }

    // @PostMapping("/update")
    // public ResponseEntity<GenericResponse<?>> updateStudentProfile(@RequestBody
    // StudentDto dto) {

    // return ResponseEntity.ok(
    // GenericResponse.builder()
    // .statusCode(200)
    // .message("Profile Updated.")
    // .data(studentService.update(dto))
    // .build());
    // }

}
