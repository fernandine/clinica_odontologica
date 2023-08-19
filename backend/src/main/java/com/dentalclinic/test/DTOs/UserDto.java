package com.dentalclinic.test.DTOs;

import com.dentalclinic.test.entities.Address;
import com.dentalclinic.test.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UserDto implements Serializable {

    private Long id;
    @NotBlank(message = "Campo obrigatório")
    private String name;
    private String phone;
    private Instant appointmentDate;
    private String dentist;
    private String description;

    @Email(message = "Favor entrar com email válido")
    private String email;
    private List<String> roles = new ArrayList<>();
    private List<AddressDto> addressList = new ArrayList<>();

    public UserDto() {
    }

    public UserDto(Long id, String name, String phone, Instant appointmentDate,
                   String dentist, String description, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.appointmentDate = appointmentDate;
        this.dentist = dentist;
        this.description = description;
        this.email = email;
    }

    public UserDto(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        phone = entity.getPhone();
        appointmentDate = entity.getAppointmentDate();
        dentist = entity.getDentist();
        description = entity.getDescription();

        for (Address address : entity.getAddressList()) {
            AddressDto addressDto = new AddressDto(address);
            addressList.add(addressDto);
        }

        for (GrantedAuthority role : entity.getAuthorities()) {
            roles.add(role.getAuthority());
        }
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<AddressDto> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressDto> addressList) {
        this.addressList = addressList;
    }
}