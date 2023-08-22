package com.dentalclinic.test.services;

import com.dentalclinic.test.DTOs.AddressDto;
import com.dentalclinic.test.entities.Address;
import com.dentalclinic.test.entities.Patient;
import com.dentalclinic.test.entities.User;
import com.dentalclinic.test.repositories.AddressRepository;
import com.dentalclinic.test.repositories.PatientRepository;
import com.dentalclinic.test.repositories.UserRepository;
import com.dentalclinic.test.services.exceptions.DatabaseException;
import com.dentalclinic.test.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private PatientRepository patientRepository;

    @Transactional(readOnly = true)
    public List<AddressDto> findAll() {
        List<Address> list = repository.findAll();
        return list.stream().map(AddressDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AddressDto> getByPatientId(Long patientId) {
        Patient patient = patientRepository.getReferenceById(patientId);
        List<Address> list = repository.findbyPatientId(patient);
        return list.stream().map(AddressDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AddressDto findById(Long id) {
        Optional<Address> obj = repository.findById(id);
        Address entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new AddressDto(entity);
    }

    @Transactional
    public AddressDto insert(AddressDto dto) {
        Address entity = new Address();

        entity.setCep(dto.getCep());
        entity.setBairro(dto.getBairro());
        entity.setComplemento(dto.getComplemento());
        entity.setLogradouro(dto.getLogradouro());
        entity.setLocalidade(dto.getLocalidade());

        Patient patient = new Patient();
        patient.setId(dto.getPatientId());
        entity.setPatient(patient);

        entity = repository.save(entity);
        return new AddressDto(entity);
    }

    @Transactional
    public AddressDto update(Long id, AddressDto dto) {
        try {
            Address entity = repository.getReferenceById(id);
            entity.setCep(dto.getCep());
            entity.setBairro(dto.getBairro());
            entity.setComplemento(dto.getComplemento());
            entity.setLogradouro(dto.getLogradouro());
            entity.setLocalidade(dto.getLocalidade());
            entity = repository.save(entity);
            return new AddressDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}

