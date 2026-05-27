package com.olp.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.olp.dto.request.CourseDto;
import com.olp.dto.response.CourseResponseDto;
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

        /*
         * MAPPER
         */
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

        public CourseResponseDto mapToCourseResponseDto(Course course) {

                return CourseResponseDto.builder()
                                .id(course.getId())
                                .name(course.getName())
                                .subjectCode(course.getSubjectCode())
                                .description(course.getDescription())
                                .category(course.getCategory())

                                .instructorName(
                                                course.getInstructor() != null
                                                                ? course.getInstructor().getName()
                                                                : null)

                                .instructorEmail(
                                                course.getInstructor() != null
                                                                ? course.getInstructor().getEmail()
                                                                : null)

                                .institutionName(
                                                course.getInstitution() != null
                                                                ? course.getInstitution().getName()
                                                                : null)

                                .build();
        }

        /*
         * SAVE COURSE
         */
        public CourseResponseDto saveCourse(CourseDto req) {
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

                Course saved = courseRepository.save(course);
                return mapToCourseResponseDto(saved);
        }

        /*
         * LIST ALL
         */
        public List<Course> getAllCourses() {
                return courseRepository.findAll();
        }

        /*
         * GET BY ID
         */
        public Course getCourseById(Long courseId) {
                return courseRepository
                                .findById(courseId)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Course Not Found"));
        }

        /*
         * UPDATE
         */
        public CourseResponseDto updateCourse(Long courseId, CourseDto dto) {

                // LOGGED-IN INSTRUCTOR
                String email = SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName();

                Instructor instructor = instructorRepository
                                .findByEmail(email)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Instructor Not Found"));

                // FETCH COURSE
                Course course = courseRepository
                                .findById(courseId)
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Course Not Found"));

                // SECURITY CHECK
                if (!course.getInstructor()
                                .getId()
                                .equals(instructor.getId())) {
                        throw new RuntimeException("You are not authorized to update this course");
                }

                // UPDATE FIELDS
                course.setName(dto.getName());
                course.setDescription(dto.getDescription());
                course.setCategory(dto.getCategory());

                // OPTIONAL INSTITUTION UPDATE
                if (dto.getInstitutionId() != null) {
                        Institution institution = institutionRepository
                                        .findById(dto.getInstitutionId())
                                        .orElseThrow(() -> new EntityNotFoundException("Institution Not Found"));
                        course.setInstitution(institution);
                }

                Course update = courseRepository.save(course);

                return mapToCourseResponseDto(update);
        }
}
