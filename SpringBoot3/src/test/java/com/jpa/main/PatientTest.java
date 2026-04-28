package com.jpa.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.jpa.main.dto.BloodgroupCount;
import com.jpa.main.entity.BloodGroup;
import com.jpa.main.entity.Patient;
import com.jpa.main.repository.PatientRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PatientTest {

    private static final Logger log = LoggerFactory.getLogger(PatientTest.class);

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testTransaction() {
        Patient p1 = patientRepository.findByName("Diya Patel");
        assertNotNull(p1); // ensures data exists
        assertEquals("Diya Patel", p1.getName());

        System.out.println(p1.toString());

        List<Patient> ls = patientRepository
                .findByEmailOrBirthDate("aarav.sharma@example.com", LocalDate.of(1990, 05, 20));
        for (Patient p : ls) {
            System.out.println(p.toString());
        }
    }

    @Test
    public void test2() {
        List<Patient> ls = patientRepository.findByBirthDateBetween(LocalDate.of(1985, 01, 01),
                LocalDate.of(1990, 01, 01));
        assertNotNull(ls);
        assertTrue(ls.size() > 0);
    }

    @Test
    public void testTransaction2() {
        Patient p1 = patientRepository.findByName("Diya Patel");

        assertNotNull(p1);
        log.info("Patient: {}", p1);
    }

    @Test
    public void test3() {
        List<Patient> ls = patientRepository.findByNameContaining("Di");
        assertNotNull(ls);
        assertTrue(ls.size() > 0);

        List<Patient> ls2 = patientRepository.findByNameContainingOrderByIdDesc("Di");
    }

    @Test
    public void test4() {
        List<Patient> ls = patientRepository.findByBloodGroup(BloodGroup.A_POSITIVE);
    }

    @Test
    public void test5() {
        List<Patient> ls = patientRepository.findByNameIgnoreCase("D", "Neha");
    }

    @Test
    public void test6() {
        List<Patient> ls = patientRepository.findByBloodGroup(BloodGroup.A_POSITIVE);
    }

    @Test
    public void test7() {
        List<Patient> ls = patientRepository.findByBornAfterDate(LocalDate.of(1985, 01, 01));
    }

    @Test
    public void test8() {
        List<BloodgroupCount> ls = patientRepository.countEachBloodGroupType();
        // List<Object[]> ls = patientRepository.countEachBloodGroupType();
    }

    @Test
    public void test9() {
        // List<Patient> sl = patientRepository.findAllPatient();
        Page<Patient> patientList = patientRepository.findAllPatient(PageRequest.of(0, 2, Sort.by("name")));
    }

    @Test
    public void test10() {
        int rowsUpdated = patientRepository.updatePatientWithId("Arav Sharma", 1L);
    }

}