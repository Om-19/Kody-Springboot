package com.onlinelearning.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Enrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate enrollmentDate;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "student_id")
	private Student student;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "course_id")
	private Course course;
}
