package com.dentalclinic.test.services;

import com.dentalclinic.test.DTOs.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url="viacep.com.br/ws/", name="viacep")
public interface ViaCepService {

    @GetMapping("/{cep}/json/")
    AddressDto findByCep(@PathVariable("cep") String cep);
}

