package com.jpa.main;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.jpa.main.dto.PatientSummary;
import com.jpa.main.entity.BloodGroup;
import com.jpa.main.entity.Patient;
import com.jpa.main.repository.PRpract;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PRtest {

    @Autowired
    private PRpract repo;

    @Test
    public void test1() {
        List<Patient> ls = repo.findByNameContainingIgnoreCaseAndBloodGroupAndBirthDateAfter(
                "Di",
                BloodGroup.A_POSITIVE,
                LocalDate.of(1985, 01, 01));
    }

    @Test
    public void test2() {
        Optional<Patient> p = repo.findByName("Arav Sharma");
    }

    @Test
    public void test3() {
        Page<Patient> ls = repo.findByNameContainingIgnoreCase("a",
                PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "birthDate")));
        List<Patient> patients = ls.getContent();
    }

    @Test
    public void test4() {
        List<Patient> ls = repo.findByBloodGroupAndBirthDateBetweenAndNameContainingIgnoreCase(
                BloodGroup.A_POSITIVE, LocalDate.of(1985, 01, 01), LocalDate.of(1990, 01, 01), "D");
    }

    @Test
    public void test5() {
        List<Patient> ls = repo.findByBloodGroupAndBirthDateBetweenAndNameContainingIgnoreCase(
                BloodGroup.A_POSITIVE, LocalDate.of(1985, 01, 01), LocalDate.of(1995, 01, 01), "D");
    }

    @Test
    public void test6() {
        List<PatientSummary> list = repo.findAllPatientSummaries();

        assertNotNull(list);

        list.forEach(p -> System.out.println(p.getName() + " | " + p.getBloodGroup()));
    }
}
