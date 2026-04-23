package com.jpa.main.controller;

import org.springframework.web.bind.annotation.RestController;

import com.jpa.main.entity.Patient;
import com.jpa.main.repository.PatientRepository;
import com.jpa.main.service.PatientService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class PatientController {

    @Autowired
    PatientService service;

    @Autowired
    PatientRepository patientRepository;

    @GetMapping("/")
    public String getHome() {
        return "Welocme";
    }

    @GetMapping("/findByBirthDateBetween")
    public List<Patient> getMethodName() {
        return service.findByBirthDateBetween(LocalDate.of(1985, 01, 01),
                LocalDate.of(1995, 01, 01));
    }

    @GetMapping("/updateName")
    public int updateName() {
        return patientRepository.updatePatientWithId("Arav Sharma", 1L);
    }

    @GetMapping("/page0")
    public Page<Patient> getPage() {
        System.out.println();
        Page<Patient> patientList = patientRepository.findAllPatient(PageRequest.of(1, 2, Sort.by("name")));
        for (Patient p : patientList) {
            System.out.println(p.getName());
        }
        return patientList;
    }

}
