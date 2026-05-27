package com.olp.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.olp.dto.request.CourseDto;
import com.olp.entity.Course;
import com.olp.entity.Institution;
import com.olp.entity.Instructor;
import com.olp.exception.customExc.EntityAlreadyExistException;
import com.olp.repository.CoursesRepository;
import com.olp.repository.InstitutionRepository;
import com.olp.repository.InstructorRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl {

        private final CoursesRepository courseRepository;
        private final InstructorRepository instructorRepository;
        private final InstitutionRepository institutionRepository;

        public Course mapDtoToCourse(CourseDto dto, Instructor instructor, Institution institution) {
                Course course = Course.builder()
                                .name(dto.getName())
                                .description(dto.getDescription())
                                .subjectCode(dto.getSubjectCode())
                                .category(dto.getCategory())
                                .instructor(instructor)
                                .institution(institution)
                                .build();

                return course;
        }

        public String saveCourse(CourseDto req) {
                String email = SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName();

                Instructor instructor = instructorRepository
                                .findByEmail(email)
                                .orElseThrow(() -> new EntityNotFoundException("Instructor Not Found"));

                boolean exists = courseRepository
                                .existsByName(req.getName());

                if (exists) {
                        throw new EntityAlreadyExistException(
                                        "Course already exists");
                }

                Institution institution = null;
                if (req.getInstitutionId() != null) {
                        institution = institutionRepository
                                        .findById(req.getInstitutionId())
                                        .orElseThrow(() -> new EntityNotFoundException("Institution Not Found."));

                }

                Course course = mapDtoToCourse(req, instructor, institution);

                courseRepository.save(course);
                return req.getName() + " saved to DB.";
        }
}
