package com.cms.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.cms.dto.ComplainRequest;
import com.cms.entity.Complain;
import com.cms.entity.User;
import com.cms.enums.Role;
import com.cms.enums.Status;
import com.cms.repository.ComplainRepository;
import com.cms.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComplainService implements com.cms.service.Interface.ComplainService {

    private final ComplainRepository complainRepository;
    private final UserRepository userRepository;

    @Override
    public Complain createComplain(ComplainRequest complainRequest) {

        // validate if user exist
        User user = userRepository.findById(complainRequest.getUserId())
                .orElseThrow(
                        () -> new IllegalArgumentException("User not found with id: " + complainRequest.getUserId()));

        if (user.getRole() == Role.ADMIN) {
            throw new IllegalStateException("Admins cannot create complaints ");
        }

        // Map Dto -> entity
        Complain complain = new Complain();
        complain.setTitle(complainRequest.getTitle());
        complain.setDescription(complainRequest.getDescription());
        complain.setStatus(Status.OPEN);
        complain.setUser(user);
        complain.setCreatedAt(LocalDateTime.now());

        return complainRepository.save(complain);
    }

    @Override
    public Complain getComplainById(Long id) {
        return complainRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Complain not found with id: " + id));
    }

}
