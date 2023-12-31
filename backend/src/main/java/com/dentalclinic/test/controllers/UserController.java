package com.dentalclinic.test.controllers;

import com.dentalclinic.test.DTOs.UserDto;
import com.dentalclinic.test.DTOs.UserInsertDto;
import com.dentalclinic.test.DTOs.UserUpdateDto;
import com.dentalclinic.test.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Usuários", description = "Controle de usuários")
@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<List<UserDto>> findAll() {
		List<UserDto> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	//@PreAuthorize("hasAnyRole('ROLE_DENTIST', 'ROLE_ADMIN')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDto> findById(@PathVariable Long id) {
		UserDto dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

//	@PreAuthorize("hasAnyRole('ROLE_DENTIST', 'ROLE_ADMIN')")
//	@GetMapping(value = "/me")
//	public ResponseEntity<UserDto> findMe() {
//		UserDto dto = service.findMe();
//		return ResponseEntity.ok().body(dto);
//	}
	
	@PostMapping
	@ApiOperation(value = "Criação de um novo usuário")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Bad Request")
	})
	public ResponseEntity<UserDto> insert(@RequestBody @Valid UserInsertDto dto) {
		UserDto newDto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UserUpdateDto dto) {
		UserDto newDto = service.update(id, dto);
		return ResponseEntity.ok().body(newDto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
} 
