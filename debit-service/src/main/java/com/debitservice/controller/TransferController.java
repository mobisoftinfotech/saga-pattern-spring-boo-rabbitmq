package com.debitservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.debitservice.config.RabbitMQConfig;
import com.debitservice.events.TransferRequested;

@RestController
@RequestMapping("/transfer")
public class TransferController {
	private static final Logger LOGGER = LoggerFactory.getLogger(TransferController.class);

	private final RabbitTemplate rabbitTemplate;

	public TransferController(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@PostMapping
	public String requestTransfer(@RequestBody TransferRequested request) {
		LOGGER.info("Initiating transfer: {}", request.getTransferId());
		rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.RK_TRANSFER_REQUESTED, request);
		return "Transfer requested: " + request.getTransferId();
	}
}
