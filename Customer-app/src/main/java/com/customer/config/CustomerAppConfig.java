package com.customer.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerAppConfig {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
