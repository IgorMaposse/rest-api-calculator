package com.calculator.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void enviaMenssagem( String queueName, Object menssage) {

		this.rabbitTemplate.convertAndSend(queueName, menssage);
	}
	


}
