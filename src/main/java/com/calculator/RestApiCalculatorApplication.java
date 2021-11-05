package com.calculator;

import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.calculator")
public class RestApiCalculatorApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RestApiCalculatorApplication.class, args);
		
	}

}
