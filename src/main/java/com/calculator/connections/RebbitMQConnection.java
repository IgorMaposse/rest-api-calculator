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

		//Queue filaEstoque =this.fila(RabbitMQConstants.FILA_ESTOQUE);
		//Queue filaPreco =this.fila(RabbitMQConstants.FILA_PRECO);
		Queue soma= this.queue(RabbitMQConstants.FILA_SOMA);
		Queue multuplicacao= this.queue(RabbitMQConstants.FILA_MULTIPLICACAO);
		Queue subtracao= this.queue(RabbitMQConstants.FILA_SUBTRACAO);
		Queue divisao= this.queue(RabbitMQConstants.FILA_DIVISAO);
	
		//DirectExchange troca = this.trocaDirecta();
		DirectExchange exchange= this.directExchange();
		
		//Binding ligacaoPreco= this.relacionamento(filaPreco, troca);
		
		Binding connectionSoma=this.relationship(soma, exchange);
		Binding connectionSubtracao=this.relationship(subtracao, exchange);
		Binding connectionMultiplicacao=this.relationship(multuplicacao, exchange);
		Binding connectionDivisao=this.relationship(divisao, exchange);
		
		//this.amqpadmin.declareQueue(filaEstoque);
		//this.amqpadmin.declareQueue(filaPreco);
		this.amqpadmin.declareQueue(soma);
		this.amqpadmin.declareQueue(subtracao);
		this.amqpadmin.declareQueue(multuplicacao);
		this.amqpadmin.declareQueue(divisao);
		
		//this.amqpadmin.declareExchange(troca);
		this.amqpadmin.declareExchange(exchange);
		
		//this.amqpadmin.declareBinding(ligacaoEstoque);
		//this.amqpadmin.declareBinding(ligacaoPreco);
		this.amqpadmin.declareBinding(connectionSoma);
		this.amqpadmin.declareBinding(connectionSubtracao);
		this.amqpadmin.declareBinding(connectionMultiplicacao);
		this.amqpadmin.declareBinding(connectionDivisao);
		
	}

}
