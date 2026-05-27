package com.olp.service;

import org.springframework.stereotype.Service;

import com.olp.dto.request.StudentDto;
import com.olp.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    // public Object getAllStudentForCourse(Long id) {

    // }

    // public StudentDto update(StudentDto dto) {

    // }

}
