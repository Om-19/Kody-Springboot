package com.onlinelearning.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String education;
	
	@OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Enrollment> enrollments;
	
	@OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Progress> progress;
	
	@OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Review> reviews;
}
