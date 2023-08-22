package com.dentalclinic.test.controllers;

import com.dentalclinic.test.DTOs.AddressDto;
import com.dentalclinic.test.services.AddressService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Endereço", description = "Controle de endereços")
@RestController
@RequestMapping(value = "/adresses")
public class AddressController {

    @Autowired
    private AddressService service;

    //busca todos os endereços
    @GetMapping
    public ResponseEntity<List<AddressDto>> findAll() {
        List<AddressDto> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    //busca endereco por id
    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> findById(@PathVariable Long id) {
        AddressDto dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    @ApiOperation(value = "Criação de um novo endereço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<AddressDto> insert(@RequestBody AddressDto dto) {
        AddressDto newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> update(@PathVariable Long id, @RequestBody AddressDto dto) {
        AddressDto newDto = service.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
