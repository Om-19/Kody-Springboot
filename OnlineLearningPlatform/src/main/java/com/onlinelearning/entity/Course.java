package com.onlinelearning.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private Double price;
	private String level;
	private Integer durationHours;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "school_id")
	private School school;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;
	
	@OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Storage> storageFiles;
	
	@OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Enrollment> enrollments;
	
	@OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Progress> progress;
	
	@OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Review> reviews;
}
