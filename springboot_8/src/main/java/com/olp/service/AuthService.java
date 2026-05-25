package com.olp.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.olp.dto.request.LoginRequest;
import com.olp.dto.request.RegisterReqInstructor;
import com.olp.dto.request.RegisterRequest;
import com.olp.dto.response.AuthResponse;
import com.olp.entity.Instructor;
import com.olp.entity.Student;
import com.olp.exception.customExc.EntityAlreadyExistException;
import com.olp.repository.InstitutionRepository;
import com.olp.repository.InstructorRepository;
import com.olp.repository.StudentRepository;
import com.olp.security.JwtUtil;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final InstitutionRepository institutionRepository;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // AuthService(InstitutionRepository institutionRepository) {
    // this.institutionRepository = institutionRepository;
    // }

    public Student registerStudent(RegisterRequest request) {
        Student student = Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        if (studentRepository.existsByEmail(request.getEmail()) == true) {
            throw new EntityAlreadyExistException("Student With this mail already exist.");
        }

        return studentRepository.save(student);
    }

    public Instructor registerInstructor(RegisterReqInstructor request) {
        Instructor instructor = Instructor.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        String email = request.getEmail();
        if (instructorRepository.existsByEmail(email) == true) {
            throw new EntityAlreadyExistException("User with the email already exist");
        }

        return instructorRepository.save(instructor);
    }

    public AuthResponse login(LoginRequest request) {

        Student student = studentRepository.findByEmail(request.getEmail())

                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean matches = passwordEncoder.matches(
                request.getPassword(),
                student.getPassword());

        if (!matches) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(student.getEmail());

        return new AuthResponse(token);
    }

    public AuthResponse loginAsInstructor(LoginRequest req) {
        Instructor instructor = instructorRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Instructor Not Found"));

        boolean matches = passwordEncoder.matches(req.getPassword(), instructor.getPassword());

        if (!matches) {
            throw new RuntimeException("Invalid Instructor");
        }

        String token = jwtUtil.generateToken(instructor.getEmail());

        return new AuthResponse(token);
    }
}
