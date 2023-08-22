package com.dentalclinic.test.services;

import com.dentalclinic.test.DTOs.AddressDto;
import com.dentalclinic.test.DTOs.PatientDto;
import com.dentalclinic.test.DTOs.UserDto;
import com.dentalclinic.test.entities.Address;
import com.dentalclinic.test.entities.Patient;
import com.dentalclinic.test.entities.User;
import com.dentalclinic.test.repositories.AddressRepository;
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

import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository repository;

//    @Autowired
//    private ModelMapper modelMapper;

    @Autowired
    private AddressRepository addressRepository;
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
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new PatientDto(entity);
    }

    @Transactional
    public PatientDto update(Long id, PatientDto dto) {
        try {
            Patient entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
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

    private void copyDtoToEntity(PatientDto dto, Patient entity) {
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAppointmentDate(dto.getAppointmentDate());
        entity.setDentist(dto.getDentist());
        entity.setPhone(dto.getPhone());
        entity.setDescription(dto.getDescription());

        entity.getAddressList().clear();

        for (AddressDto addressDto : dto.getAddressList()) {
            Address address = new Address();
            address.setId(addressDto.getId());
            address.setLogradouro(addressDto.getLogradouro());
            address.setBairro(addressDto.getBairro());
            address.setCep(addressDto.getCep());
            address.setComplemento(addressDto.getComplemento());
            address.setLocalidade(addressDto.getLocalidade());
            address.setUf(addressDto.getUf());

            entity.getAddressList().add(address);
        }
    }
}
