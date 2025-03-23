package com.springboot.project;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PicbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicbitApplication.class, args);
	}
	
	@Bean
	public ModelMapper createModelMapper() {
		return new ModelMapper();	
	}

}
