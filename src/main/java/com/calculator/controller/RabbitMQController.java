package com.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calculator.constants.RabbitMQConstants;
import com.calculator.dto.RabbitMQDto;
import com.calculator.service.RabbitMQService;

@RestController
@RequestMapping(value = "calculate")
public class RabbitMQController {
	
	@Autowired
	RabbitMQService rabbitMQService;
	
	@PutMapping
	private ResponseEntity calculateWithDecimal(@RequestBody RabbitMQDto rabbitDto) {
		this.rabbitMQService.enviaMenssagem(RabbitMQConstants.FILA_SOMA, rabbitDto);
		return new ResponseEntity(HttpStatus.OK);
	}

	
}
