package com.olp.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.olp.entity.Course;
import com.olp.entity.Instructor;
import com.olp.entity.Storage;
import com.olp.repository.CoursesRepository;
import com.olp.repository.InstructorRepository;
import com.olp.repository.StorageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;
    private final CoursesRepository courseRepository;
    private final InstructorRepository instructorRepository;

    public String uploadMaterial(
            Long courseId,
            MultipartFile file,
            Authentication authentication) throws IOException {

        // Get User
        String email = authentication.getName();
        Instructor instructor = instructorRepository
                .findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Instrucor Doesnt exist"));

        // fetch Course
        Course course = courseRepository
                .findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course Doesnt exist"));

        // Only course owner can upload
        if (!course.getInstructor().getId()
                .equals(instructor.getId())) {
            throw new RuntimeException(
                    "You are not authorized to upload materials to this course");
        }

        String uploadDir = "uploads/";
        File directory = new File(uploadDir);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        // create unique file name
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        // save to folder
        Path path = Paths.get(uploadDir, fileName);

        Files.copy(
                file.getInputStream(),
                path,
                StandardCopyOption.REPLACE_EXISTING);

        Storage storage = Storage.builder()
                .name(fileName)
                .fileType(file.getContentType())
                .location(path.toString())
                .course(course)
                .createdAt(LocalDateTime.now())
                .build();

        storageRepository.save(storage);

        return "File Uploaded";
    }
}
