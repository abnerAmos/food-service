package com.food.service.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressResponse implements Serializable {

    private String cep;
    private String localidade;
    private String uf;
    private String bairro;
    private String logradouro;
    private boolean erro;

}
