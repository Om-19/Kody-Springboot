package com.cms.service.impl;

import org.springframework.stereotype.Service;

import com.cms.entity.User;
import com.cms.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }
}