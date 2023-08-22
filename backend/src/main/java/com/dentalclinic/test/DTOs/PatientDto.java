package com.dentalclinic.test.DTOs;

import com.dentalclinic.test.entities.Patient;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class PatientDto implements Serializable {
    private Long id;
    private String name;
    private String phone;
    private Instant appointmentDate;
    private String dentist;
    private String description;

    private Set<AddressDto> addressList = new HashSet<>();

    public PatientDto(){}

    public PatientDto(Long id, String name, String phone, Instant appointmentDate, String dentist, String description) {
        this.id = id;
        this.name = name;
        this.phone = phone;
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
        phone = entity.getPhone();
        entity.getAddressList().forEach(address -> this.addressList.add(new AddressDto(address)));
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<AddressDto> getAddressList() {
        return addressList;
    }

    public void setAddressList(Set<AddressDto> addressList) {
        this.addressList = addressList;
    }
}
