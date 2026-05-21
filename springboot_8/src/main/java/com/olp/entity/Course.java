package com.olp.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.olp.entity.enums.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank // because null + empty spaces both invalid.
    private String name;

    @NotNull
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    // MANY Courses belong to ONE Instructor
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    // Also nullable by default.
    // So optional institution works automatically.
    @ManyToOne // One institution can have MANY courses.
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @OneToMany(mappedBy = "course")
    @JsonManagedReference
    private List<Review> reviews;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;

}
