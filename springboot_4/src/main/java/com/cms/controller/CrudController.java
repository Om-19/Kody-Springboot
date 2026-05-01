package com.cms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.dto.ComplainRequest;
import com.cms.dto.ComplainResponse;
import com.cms.entity.Complain;
import com.cms.entity.User;
import com.cms.enums.Role;
import com.cms.service.impl.ComplainService;
import com.cms.service.impl.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cms")
@RequiredArgsConstructor
public class CrudController {

    private final UserService userService;
    private final ComplainService complainService;

    @GetMapping("/home")
    public String home() {
        return "Welcome";
    }

    @PostMapping("/createUser")
    public String cr(@RequestBody User user) {
        userService.saveUser(user);

        if (user.getRole().equals(Role.ADMIN)) {
            return "Admin registered Successfully.";
        }
        return "User registered Successfully.";
    }

    @PostMapping("/createComplaint")
    public ResponseEntity<ComplainResponse> createComplaintEntity(
            @Valid @RequestBody ComplainRequest complainRequest) {

        Complain complain = complainService.createComplain(complainRequest);

        ComplainResponse response = new ComplainResponse(
                complain.getId(),
                complain.getTitle(),
                complain.getStatus(),
                complain.getCreatedAt());

        /*
         * ResponseEntity allows us to control the complete HTTP response including
         * status code, headers, and body, making APIs more flexible and REST-compliant.
         */
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

}
