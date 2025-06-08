package com.debitservice.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.debitservice.config.RabbitMQConfig;
import com.debitservice.events.CreditFailed;
import com.debitservice.events.DebitCompleted;
import com.debitservice.events.DebitFailed;
import com.debitservice.events.TransferRequested;
import com.debitservice.store.AccountStore;

@Component
public class DebitEventHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(DebitEventHandler.class);

	private final RabbitTemplate rabbitTemplate;
	private final AccountStore accountStore;

	public DebitEventHandler(RabbitTemplate rabbitTemplate, AccountStore accountStore) {
		this.rabbitTemplate = rabbitTemplate;
		this.accountStore = accountStore;
	}

	@RabbitListener(queues = RabbitMQConfig.QUEUE_TRANSFER_REQUESTED)
	public void handleTransferRequest(TransferRequested request) {
		LOGGER.info("Received TransferRequested: {}", request.getTransferId());

		boolean success = accountStore.debit(request.getFromAccount(), request.getAmount());
		if (success) {
			LOGGER.info("Debit succeeded for {} of amount {}", request.getFromAccount(), request.getAmount());
			DebitCompleted event = new DebitCompleted(request.getTransferId(), request.getFromAccount(),
					request.getToAccount(), request.getAmount());
			rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.RK_DEBIT_COMPLETED, event);
			LOGGER.info("Published DebitCompleted: {}", event);
		} else {
			LOGGER.warn("Debit failed for {} of amount {}. Insufficient funds.", request.getFromAccount(),
					request.getAmount());
			DebitFailed event = new DebitFailed(request.getTransferId(),
					"Insufficient balance in account: " + request.getFromAccount());
			rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.RK_DEBIT_FAILED, event);
			LOGGER.info("Published DebitFailed: {}", event);
		}
	}

	@RabbitListener(queues = RabbitMQConfig.QUEUE_CREDIT_FAILED)
	public void handleCreditFailed(CreditFailed event) {
		LOGGER.warn("Received CreditFailed for transfer {}. Reason: {}. Refunding amount {}. To account {}.",
				event.getTransferId(), event.getReason(), event.getRefundAmount(), event.getRefundAccount());
		accountStore.credit(event.getRefundAccount(), event.getRefundAmount());
		LOGGER.info("Refunded {} to account {}. New balance: {}", event.getRefundAmount(), event.getRefundAccount(),
				accountStore.getBalance(event.getRefundAccount()));
	}
}
