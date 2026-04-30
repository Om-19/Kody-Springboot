package com.jpa.main.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.main.dto.PatientSearchCriteria;
import com.jpa.main.entity.BloodGroup;
import com.jpa.main.entity.Patient;
import com.jpa.main.repository.PatientRepository;
import com.jpa.main.service.CriteriaService;
import com.jpa.main.service.PatientService;
import com.jpa.main.service.SpecificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PatientController {

    private final PatientService service;
    private final CriteriaService cService;
    private final SpecificationService specService;

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

    // Pagination
    @GetMapping("/page0")
    public Page<Patient> getPage() {
        System.out.println();
        Page<Patient> patientList = patientRepository.findAllPatient(PageRequest.of(1, 2, Sort.by("name")));
        for (Patient p : patientList) {
            System.out.println(p.getName());
        }
        return patientList;
    }

    @GetMapping("/getPatientFromCriteria")
    public List<Patient> searchPatients(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) BloodGroup bloodGroup) {

        PatientSearchCriteria sc = new PatientSearchCriteria();
        sc.setName(name);
        sc.setBloodGroup(bloodGroup);

        return cService.searchPatients(sc);
    }

    @PostMapping("/searchWithSpecification")
    public List<Patient> searchPatients(@RequestBody PatientSearchCriteria sc) {
        return specService.searchPatients(sc);
    }
}
