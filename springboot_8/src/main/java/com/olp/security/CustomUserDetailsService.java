package com.olp.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.olp.entity.Student;
import com.olp.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

        private final StudentRepository studentRepository;

        @Override
        public UserDetails loadUserByUsername(String email)
                        throws UsernameNotFoundException {

                Student student = studentRepository.findByEmail(email)

                                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

                return User.builder()

                                .username(student.getEmail())

                                .password(student.getPassword())

                                .roles(student.getRole().name())

                                .build();
        }
}