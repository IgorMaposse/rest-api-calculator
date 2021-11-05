package com.calculator.connections;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import com.calculator.constants.RabbitMQConstants;

@Component
public class RebbitMQConnection {
	
	private static final String NAME_EXCHANGE="amq.direct";
	private AmqpAdmin amqpadmin;
	

	public RebbitMQConnection(AmqpAdmin amqpadmin) {
		this.amqpadmin= amqpadmin;
	}

	private Queue queue(String queueName) {
		return	new Queue(queueName,true,false,false);
	}
	
	private DirectExchange directExchange() {
		return new DirectExchange(NAME_EXCHANGE);
	}
	
	private Binding relationship(Queue queue, DirectExchange exchange) {
		return new Binding(queue.getName(),Binding.DestinationType.QUEUE, exchange.getName(),queue.getName(),null);
	}
	
	@PostConstruct
	private void addToQueue() {

		Queue sum= this.queue(RabbitMQConstants.FILA_SUM);
		Queue mult= this.queue(RabbitMQConstants.FILA_MULT);
		Queue subt= this.queue(RabbitMQConstants.FILA_SUBT);
		Queue div= this.queue(RabbitMQConstants.FILA_DIV);
	
		
		DirectExchange exchange= this.directExchange();
		
		
		Binding connectionSum=this.relationship(sum, exchange);
		Binding connectionSubt=this.relationship(mult, exchange);
		Binding connectionMult=this.relationship(subt, exchange);
		Binding connectionDiv=this.relationship(div, exchange);
		
		this.amqpadmin.declareQueue(sum);
		this.amqpadmin.declareQueue(mult);
		this.amqpadmin.declareQueue(subt);
		this.amqpadmin.declareQueue(div);
		
		
		this.amqpadmin.declareExchange(exchange);
		
		
		this.amqpadmin.declareBinding(connectionSum);
		this.amqpadmin.declareBinding(connectionSubt);
		this.amqpadmin.declareBinding(connectionMult);
		this.amqpadmin.declareBinding(connectionDiv);
		
	}

}
