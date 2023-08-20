package com.dentalclinic.test.DTOs;

import com.dentalclinic.test.entities.Patient;
import jakarta.persistence.Column;

import java.io.Serializable;
import java.time.Instant;

public class PatientDto implements Serializable {
    private Long id;
    private String name;
    private Instant appointmentDate;
    private String dentist;
    private String description;

    public PatientDto(){}

    public PatientDto(Long id, String name, Instant appointmentDate,
                      String dentist, String description) {
        this.id = id;
        this.name = name;
        this.appointmentDate = appointmentDate;
        this.dentist = dentist;
        this.description = description;
    }
    public PatientDto(Patient entity) {
        id = entity.getId();
        name = entity.getName();
        appointmentDate = entity.getAppointmentDate();
        dentist = entity.getDentist();
        description = entity.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Instant appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getDentist() {
        return dentist;
    }

    public void setDentist(String dentist) {
        this.dentist = dentist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
