package com.olp.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.olp.dto.response.InstitutionResponseDto;
import com.olp.dto.response.InstructorSimpleDto;
import com.olp.entity.Institution;
import com.olp.repository.InstitutionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    public InstitutionResponseDto mapToDto(
            Institution institution) {

        Set<InstructorSimpleDto> instructors = institution.getInstructors()
                .stream()
                .map(ins -> InstructorSimpleDto.builder()
                        .id(ins.getId())
                        .name(ins.getName())
                        .email(ins.getEmail())
                        .build())
                .collect(Collectors.toSet());

        return InstitutionResponseDto.builder()
                .id(institution.getId())
                .name(institution.getName())
                .website(institution.getWebsite())
                .instructors(instructors)
                .build();
    }

    public List<InstitutionResponseDto> getAll() {
        log.info("\nGet All Service Method");
        return institutionRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

}
