package com.creditservice.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.creditservice.config.RabbitMQConfig;
import com.creditservice.events.CreditCompleted;
import com.creditservice.events.CreditFailed;
import com.creditservice.events.DebitCompleted;
import com.creditservice.store.AccountStore;

@Component
public class CreditEventHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(CreditEventHandler.class);

	private final RabbitTemplate rabbitTemplate;
	private final AccountStore accountStore;

	public CreditEventHandler(RabbitTemplate rabbitTemplate, AccountStore accountStore) {
		this.rabbitTemplate = rabbitTemplate;
		this.accountStore = accountStore;
	}

	@RabbitListener(queues = RabbitMQConfig.QUEUE_DEBIT_COMPLETED)
	public void handleDebitCompleted(DebitCompleted event) {
		LOGGER.info("Credit Service received DebitCompleted: {}", event.getTransferId());

		// Simulate a failure for a specific transferId "FAIL-CREDIT"
		if ("FAIL-CREDIT".equals(event.getTransferId())) {
			CreditFailed cf = new CreditFailed(event.getTransferId(), "Simulated credit failure");
			rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.RK_CREDIT_FAILED, cf);
			LOGGER.warn("Published CreditFailed: {}", cf);
			return;
		}

		accountStore.credit(event.getToAccount(), event.getAmount());
		LOGGER.info("Credited {} to {} from {}", event.getAmount(), event.getToAccount(), event.getFromAccount());

		CreditCompleted cc = new CreditCompleted(event.getTransferId(), event.getFromAccount(), event.getAmount());
		rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.RK_CREDIT_COMPLETED, cc);
		LOGGER.info("Published CreditCompleted: {}", cc);
	}
}
