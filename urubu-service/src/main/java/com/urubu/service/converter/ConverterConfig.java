package com.urubu.service.converter;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConverterConfig {


    @Bean
    public ModelMapper mapper() {
        ModelMapper mapper = new ModelMapper();
        setModelToDomain(mapper);
        setDomainToModel(mapper);
        setClientToSomething(mapper);
        return mapper;
    }

	private void setClientToSomething(ModelMapper mapper) {
	}

	private void setDomainToModel(ModelMapper mapper) {
	}

	private void setModelToDomain(ModelMapper mapper) {
	}

}
