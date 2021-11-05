package com.calculator.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calculator.constants.RabbitMQConstants;
import com.calculator.dto.RabbitMQDto;
import com.calculator.service.RabbitMQService;

@RestController
@RequestMapping("/calculate")
public class RabbitMQController {
	
	@Autowired
	RabbitMQService rabbitMQService;
	
	@PutMapping("/sum")
	private ResponseEntity calculateSumDecimal(@RequestBody RabbitMQDto rabbitDto) {
		this.rabbitMQService.sendMenssage(RabbitMQConstants.FILA_SUM, rabbitDto);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PutMapping("/sub")
	private ResponseEntity calculateSubDecimal(@RequestBody RabbitMQDto rabbitDto) {
		this.rabbitMQService.sendMenssage(RabbitMQConstants.FILA_SUBT, rabbitDto);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PutMapping("/mul")
	private ResponseEntity calculateMulDecimal(@RequestBody RabbitMQDto rabbitDto) {
		this.rabbitMQService.sendMenssage(RabbitMQConstants.FILA_MULT, rabbitDto);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PutMapping("/div")
	private ResponseEntity calculateDivDecimal(@RequestBody RabbitMQDto rabbitDto) {
		this.rabbitMQService.sendMenssage(RabbitMQConstants.FILA_DIV, rabbitDto);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	 @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
	    void handleBadRequests(HttpServletResponse response) throws IOException {
	        response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid parameter informed!");
	    }

	
}
