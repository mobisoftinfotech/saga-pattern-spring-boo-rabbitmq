package com.creditservice.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditservice.store.AccountStore;

@RestController
@RequestMapping("/accounts")
public class BalanceController {
	private final AccountStore accountStore;

	public BalanceController(AccountStore accountStore) {
		this.accountStore = accountStore;
	}

	@GetMapping
	public Map<String, Double> getAccounts() {
		return accountStore.getAccounts();
	}
}
