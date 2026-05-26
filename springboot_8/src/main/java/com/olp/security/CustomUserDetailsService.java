package com.olp.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

                System.out.println("METHOD CALLED");

                Student student = studentRepository.findByEmail(email)
                                .orElse(null);

                System.out.println(student);

                if (student == null) {
                        throw new UsernameNotFoundException("User not found");
                }

                System.out.println(student.getRole());

                return User.builder()
                                .username(student.getEmail())
                                .password(student.getPassword())
                                .roles(student.getRole().name())
                                .build();
        }
}