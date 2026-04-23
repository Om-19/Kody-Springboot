package com.jpa.main.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jpa.main.entity.BloodGroup;
import com.jpa.main.entity.BloodgroupCount;
import com.jpa.main.entity.Patient;

import jakarta.transaction.Transactional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    // findBy + attribute(in camelCase )
    Patient findByName(String name);

    List<Patient> findByEmailOrBirthDate(String email, LocalDate birthDate);

    List<Patient> findByBirthDateBetween(LocalDate from, LocalDate to);

    List<Patient> findByNameContaining(String name);

    List<Patient> findByNameContainingOrderByIdDesc(String name);

    // List<Patient> findByBloodGroup(BloodGroup bloodGroup);

    List<Patient> findByNameIgnoreCase(String name, String ignore);

    /*
     * Using @Query
     */
    @Query("Select p from Patient p Where p.bloodGroup = ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroup bloodGroup);

    @Query("Select p from Patient p Where p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(LocalDate birthDate);

    // Projection : Only Possible with JPQL & not with nativeQuery
    @Query("Select new com.jpa.main.entity.BloodgroupCount(p.bloodGroup, Count(p)) From Patient p Group By p.bloodGroup")
    List<BloodgroupCount> countEachBloodGroupType();
    // List<Object[]> countEachBloodGroupType();

    // Raw Query
    @Query(value = "Select * from patient", nativeQuery = true)
    // List<Patient> findAllPatient();
    Page<Patient> findAllPatient(Pageable pageable);

    /*
     * Update Query
     */
    @Transactional
    @Modifying
    @Query("update Patient p SET p.name = :name where p.id = :id")
    int updatePatientWithId(@Param("name") String name, @Param("id") Long id);

}
