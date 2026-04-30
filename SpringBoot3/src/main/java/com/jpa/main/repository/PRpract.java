package com.jpa.main.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jpa.main.dto.PatientSummary;
import com.jpa.main.entity.BloodGroup;
import com.jpa.main.entity.Patient;

@Repository
public interface PRpract extends JpaRepository<Patient, Long> {
        /*
         * 1. Multi-Condition Derived Query
         * Find patients whose name contains a keyword AND
         * blood group matches AND birthDate is after a given date
         */
        List<Patient> findByNameContainingIgnoreCaseAndBloodGroupAndBirthDateAfter(
                        String name,
                        BloodGroup bloodGroup,
                        LocalDate birthDate);

        /*
         * 2) Optional + Null Safety
         * Modify: Patient findByName(String name);
         */
        Optional<Patient> findByName(String name); // use only when you know result will be max one quantity

        List<Patient> findByNameContainingIgnoreCase(String name);

        /*
         * 3) Patients whose name contains "a", sorted by birthDate descending, with
         * pagination
         */
        Page<Patient> findByNameContainingIgnoreCase(String name, Pageable pageable);

        /*
         * 4)
         * Write JPQL query:
         * Get patients where:
         * bloodGroup = X
         * AND birthDate between two dates
         * AND name contains keyword
         */
        List<Patient> findByBloodGroupAndBirthDateBetweenAndNameContainingIgnoreCase(
                        BloodGroup bloodGroup,
                        LocalDate from,
                        LocalDate to,
                        String name);

        @Query("""
                        Select p from Patient p
                        where p.bloodGroup = :bloodGroup
                        And p.birthDate between :from And :to
                        And Lower(p.name) Like Lower(Concat('%', :name, '%'))
                        """)
        List<Patient> findPatientWithFilter(
                        @Param("bloodGroup") BloodGroup bloodGroup,
                        @Param("from") LocalDate from,
                        @Param("to") LocalDate to,
                        @Param("name") String name);

        /*
         * DTO Projection (Advanced)
         * Create a DTO
         * class PatientSummary {
         * String name;
         * BloodGroup bloodGroup;
         * }
         */
        @Query("""
                            SELECT new com.jpa.main.dto.PatientSummary(p.name, p.bloodGroup)
                            FROM Patient p
                        """)
        List<PatientSummary> findAllPatientSummaries();

}
