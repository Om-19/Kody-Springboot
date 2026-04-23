package com.jpa.main.entity;

public class PatientSummary {

    private String name;
    private BloodGroup bloodGroup;

    public PatientSummary(String name, BloodGroup bloodGroup) {
        this.name = name;
        this.bloodGroup = bloodGroup;
    }

    public String getName() {
        return name;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }
}