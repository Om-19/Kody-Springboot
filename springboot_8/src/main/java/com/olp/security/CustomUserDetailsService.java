package com.olp.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.olp.entity.Instructor;
import com.olp.entity.Student;
import com.olp.repository.InstructorRepository;
import com.olp.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CustomUserDetailsService
                implements UserDetailsService {

        private final StudentRepository studentRepository;

        private final InstructorRepository instructorRepository;

        @Override
        public UserDetails loadUserByUsername(String email)
                        throws UsernameNotFoundException {

                // =========================
                // CHECK STUDENT
                // =========================

                Student student = studentRepository
                                .findByEmail(email)
                                .orElse(null);

                if (student != null) {

                        return User.builder()
                                        .username(student.getEmail())
                                        .password(student.getPassword())
                                        .roles(student.getRole().name())
                                        .build();
                }

                // =========================
                // CHECK INSTRUCTOR
                // =========================

                Instructor instructor = instructorRepository
                                .findByEmail(email)
                                .orElse(null);

                if (instructor != null) {
                        return User.builder()
                                        .username(instructor.getEmail())
                                        .password(instructor.getPassword())
                                        .roles(instructor.getRole().name())
                                        .build();
                }

                // =========================
                // USER NOT FOUND
                // =========================

                throw new UsernameNotFoundException(
                                "User not found");
        }
}