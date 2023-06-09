package com.food.service.client;

import com.food.service.dto.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br") // Host da onde será consumido a API
public interface ViaCepClient {

    @GetMapping("/ws/{cep}/json") // URI da onde será consumido a API
    AddressResponse getAddressByCep(@PathVariable("cep") String cep);
}
