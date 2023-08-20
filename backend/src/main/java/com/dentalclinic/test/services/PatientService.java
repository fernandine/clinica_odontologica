package com.dentalclinic.test.services;

import com.dentalclinic.test.DTOs.PatientDto;
import com.dentalclinic.test.entities.Patient;
import com.dentalclinic.test.repositories.PatientRepository;
import com.dentalclinic.test.services.exceptions.DatabaseException;
import com.dentalclinic.test.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {
    @Autowired
    private PatientRepository repository;

    @Transactional(readOnly = true)
    public Page<PatientDto> findAll(Pageable pageable) {
        Page<Patient> page = repository.findAll(pageable);
        return page.map(x -> new PatientDto(x));

    }
    @Transactional(readOnly = true)
    public PatientDto findById(Long id) {
        Optional<Patient> obj = repository.findById(id);
        Patient entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new PatientDto(entity);
    }

    @Transactional
    public PatientDto insert(PatientDto dto) {
        Patient entity = new Patient();

        entity.setName(dto.getName());
        entity.setAppointmentDate(dto.getAppointmentDate());
        entity.setDentist(dto.getDentist());
        entity.setDescription(dto.getDescription());

        entity = repository.save(entity);
        return new PatientDto(entity);
    }

    @Transactional
    public PatientDto update(Long id, PatientDto dto) {
        try {
            Patient entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            entity.setAppointmentDate(dto.getAppointmentDate());
            entity.setDentist(dto.getDentist());
            entity.setDescription(dto.getDescription());
            entity = repository.save(entity);
            return new PatientDto(entity);
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
