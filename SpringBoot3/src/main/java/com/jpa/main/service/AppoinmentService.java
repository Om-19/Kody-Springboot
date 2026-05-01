package com.jpa.main.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa.main.dto.AppointmentDTO;
import com.jpa.main.entity.Appoinment;
import com.jpa.main.entity.Department;
import com.jpa.main.entity.Doctor;
import com.jpa.main.entity.Patient;
import com.jpa.main.repository.AppoinmentRepository;
import com.jpa.main.repository.DoctorRepository;
import com.jpa.main.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppoinmentService {

    private final AppoinmentRepository appoinmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    /*
     * No need for cascading as we dont want to create/delete any new Patient/Doctor
     * Also Appoinment is manyToOne So generally this relationship has no need for
     * cascading
     * But cascade will be required in Patient on list(appoinment) so that if
     * patient
     * gets removed
     * all appoinments hsould be deleted.
     */
    @Transactional
    public Appoinment createAppoinment(Appoinment appoinment, Long doctorId, Long patientId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Patient patient = patientRepository.findById(patientId).orElseThrow();

        if (appoinment.getId() != null)
            throw new IllegalArgumentException("Appoinment Should not have Id");

        appoinment.setPatient(patient);
        appoinment.setDoctor(doctor);

        patient.getAppoinment().add(appoinment); // to maintain consistency

        return appoinmentRepository.save(appoinment);
    }

    @Transactional
    public Appoinment assignAppoinmentToAnotherDoctor(Long appoinmentId, Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
        Appoinment appoinment = appoinmentRepository.findById(appoinmentId).orElseThrow();

        appoinment.setDoctor(doctor); // this will automatically the update as appoinment will get dirty
        doctor.getAppoinmentList().add(appoinment); // // for bi-directional consistency

        return appoinment;
    }

    public List<AppointmentDTO> findWithEntityGraph() {
        List<Appoinment> list = appoinmentRepository.findAll();

        return list.stream().map(a -> {

            AppointmentDTO dto = new AppointmentDTO();

            dto.setId(a.getId());
            dto.setReason(a.getReason());
            dto.setAppointmentDate(a.getAppoinmentDate());

            dto.setPatientName(a.getPatient().getName());
            dto.setDoctorName(a.getDoctor().getName());

            // handling Many to many (doctor -> department)
            dto.setDepartmentName(
                    a.getDoctor().getDepartments().stream()
                            .findFirst()
                            .map(Department::getName)
                            .orElse(null));

            System.out.println(a.getDoctor().getDepartments());

            return dto;

        }).toList();
    }

    public List<AppointmentDTO> findByReason(String reason) {

        List<Appoinment> list = appoinmentRepository.findByReason(reason);

        return list.stream().map(a -> {
            AppointmentDTO dto = new AppointmentDTO();

            dto.setId(a.getId());
            dto.setReason(a.getReason());
            dto.setAppointmentDate(a.getAppoinmentDate());

            dto.setPatientName(a.getPatient().getName());

            return dto;

        }).toList();
    }

    // Fetch graph example
    @Transactional(readOnly = true)
    public List<AppointmentDTO> findAllAppt() {
        return appoinmentRepository.findWithLoadGraph()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    private AppointmentDTO mapToDto(Appoinment a) {
        AppointmentDTO dto = new AppointmentDTO();

        dto.setId(a.getId());
        dto.setReason(a.getReason());
        dto.setAppointmentDate(a.getAppoinmentDate());

        // SAFE (fetched via EntityGraph)
        dto.setDoctorName(a.getDoctor().getName());

        return dto;
    }
}
