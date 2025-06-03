package com.creditservice.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	public static final String EXCHANGE = "money.transfer.exchange";

	public static final String QUEUE_TRANSFER_REQUESTED = "transfer.requested.queue";
	public static final String QUEUE_DEBIT_COMPLETED = "debit.completed.queue";
	public static final String QUEUE_DEBIT_FAILED = "debit.failed.queue";
	public static final String QUEUE_CREDIT_COMPLETED = "credit.completed.queue";
	public static final String QUEUE_CREDIT_FAILED = "credit.failed.queue";

	public static final String RK_TRANSFER_REQUESTED = "transfer.requested";
	public static final String RK_DEBIT_COMPLETED = "debit.completed";
	public static final String RK_DEBIT_FAILED = "debit.failed";
	public static final String RK_CREDIT_COMPLETED = "credit.completed";
	public static final String RK_CREDIT_FAILED = "credit.failed";

	@Bean
	DirectExchange sagaExchange() {
		return new DirectExchange(EXCHANGE);
	}

	@Bean
	Queue transferRequestedQueue() {
		return QueueBuilder.durable(QUEUE_TRANSFER_REQUESTED).build();
	}

	@Bean
	Queue debitCompletedQueue() {
		return QueueBuilder.durable(QUEUE_DEBIT_COMPLETED).build();
	}

	@Bean
	Queue debitFailedQueue() {
		return QueueBuilder.durable(QUEUE_DEBIT_FAILED).build();
	}

	@Bean
	Queue creditCompletedQueue() {
		return QueueBuilder.durable(QUEUE_CREDIT_COMPLETED).build();
	}

	@Bean
	Queue creditFailedQueue() {
		return QueueBuilder.durable(QUEUE_CREDIT_FAILED).build();
	}

	@Bean
	Binding bindTransferRequested(Queue transferRequestedQueue, DirectExchange exchange) {
		return BindingBuilder.bind(transferRequestedQueue).to(exchange).with(RK_TRANSFER_REQUESTED);
	}

	@Bean
	Binding bindDebitCompleted(Queue debitCompletedQueue, DirectExchange exchange) {
		return BindingBuilder.bind(debitCompletedQueue).to(exchange).with(RK_DEBIT_COMPLETED);
	}

	@Bean
	Binding bindDebitFailed(Queue debitFailedQueue, DirectExchange exchange) {
		return BindingBuilder.bind(debitFailedQueue).to(exchange).with(RK_DEBIT_FAILED);
	}

	@Bean
	Binding bindCreditCompleted(Queue creditCompletedQueue, DirectExchange exchange) {
		return BindingBuilder.bind(creditCompletedQueue).to(exchange).with(RK_CREDIT_COMPLETED);
	}

	@Bean
	Binding bindCreditFailed(Queue creditFailedQueue, DirectExchange exchange) {
		return BindingBuilder.bind(creditFailedQueue).to(exchange).with(RK_CREDIT_FAILED);
	}

	@Bean
	MessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}
}
