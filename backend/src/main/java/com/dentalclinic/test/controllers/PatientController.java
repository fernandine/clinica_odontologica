package com.dentalclinic.test.controllers;

import com.dentalclinic.test.DTOs.PatientDto;
import com.dentalclinic.test.services.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {

    @Autowired
    private PatientService service;

    @GetMapping
    public ResponseEntity<Page<PatientDto>> findAll(Pageable pageable) {
        Page<PatientDto> list = service.findAll(pageable);
        return ResponseEntity.ok().body(list);
    }

    //@PreAuthorize("hasRole('ROLE_DENTIST')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientDto> findPatientById (@PathVariable Long id) {
        PatientDto dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<PatientDto> insert(@RequestBody @Valid PatientDto dto) {
        PatientDto newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PatientDto> update(@PathVariable Long id, @RequestBody @Valid PatientDto dto) {
        PatientDto newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
