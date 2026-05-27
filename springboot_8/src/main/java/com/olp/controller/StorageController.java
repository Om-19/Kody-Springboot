package com.olp.controller;

import com.olp.service.StorageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/instructor/storage")
public class StorageController {

    private final StorageService storageService;

    StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload/{courseId}")
    public String postMethodName(
            @PathVariable Long courseId,
            @RequestParam("file") MultipartFile file,
            Authentication authentication) throws IOException {
        return storageService.uploadMaterial(
                courseId,
                file,
                authentication);
    }

}
