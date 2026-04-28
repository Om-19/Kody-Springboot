package com.jpa.main.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(
        // name = "patient_tbl",
        uniqueConstraints = {
                @UniqueConstraint(name = "unique_patient_email", columnNames = { "email" }),
                @UniqueConstraint(name = "unique_patient_name_birthdate", columnNames = { "name", "birthDate" })
        }, indexes = {
                @Index(name = "idx_ptn_birthDate", columnList = "birthDate")
        })
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String name;

    // @ToString.Exclude // Excludes from toString()
    private LocalDate birthDate;

    @Column(unique = true, nullable = false)
    private String email;

    private String gender;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    /*
     * OrphanRemoval will insure if patient is deleted insurance also gets deleted
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "patient_insurance_id") // Join Column indicates owning side
    private Insurance insurance;

    /*
     * orphanRemoval : if Parent deleted its appoinment, it gets automatically
     * deleted from db too
     * Also in oneToOne FetchType is EAGER
     */
    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @ToString.Exclude // can also use fetch = FetchType.EAGER, but we dont want to display the changes
                      // even before session is over
                      // if GetchType.EAGER is used it will lead to N+1 Problem
    private List<Appoinment> appoinment;

    public Patient() {
        // default Construct
    }

    public Patient(String name, LocalDate birthDate, String email, LocalDate createdAt, BloodGroup bloodGroup) {
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.createdAt = createdAt;
        this.bloodGroup = bloodGroup;
    }

    public String getName() {
        return name;
    }
}
