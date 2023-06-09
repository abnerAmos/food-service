package com.food.service.utils;

import com.food.service.client.ViaCepClient;
import com.food.service.dto.request.RestaurantRequest;
import com.food.service.dto.response.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ViaCep {

    @Autowired
    private ViaCepClient viaCepClient;

    public Optional<AddressResponse> getAddress(RestaurantRequest request) {

        AddressResponse response = viaCepClient.getAddressByCep(request.getPostalCode());

        if (response.isErro()) {
            return Optional.empty();
        }

        return Optional.of(response);
    }
}
