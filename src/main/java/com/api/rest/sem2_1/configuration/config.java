package com.api.rest.sem2_1.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class config {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
