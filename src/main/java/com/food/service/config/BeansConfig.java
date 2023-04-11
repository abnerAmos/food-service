package com.food.service.config;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        return modelMapper;
    }
}
