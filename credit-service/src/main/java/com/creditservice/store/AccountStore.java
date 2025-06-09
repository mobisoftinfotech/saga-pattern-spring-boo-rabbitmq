package com.creditservice.store;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AccountStore {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountStore.class);
	private final Map<String, Double> accounts = new ConcurrentHashMap<>(Map.of("C", 20.0, "D", 80.0));

	public Map<String, Double> getAccounts() {
		return accounts;
	}

	public Double getBalance(String acct) {
		return accounts.getOrDefault(acct, 0.0);
	}

	public void updateBalance(String acct, Double newBal) {
		LOGGER.info("Updating balance of {} to {}", acct, newBal);
		accounts.put(acct, newBal);
	}

	public boolean debit(String acct, Double amount) {
		synchronized (accounts) {
			Double balance = getBalance(acct);
			if (balance >= amount) {
				Double updated = balance - amount;
				updateBalance(acct, updated);
				LOGGER.info("Debited {} from {}. New balance: {}", amount, acct, updated);
				return true;
			} else {
				LOGGER.warn("Failed to debit {} from {}: balance {}", amount, acct, balance);
				return false;
			}
		}
	}

	public void credit(String acct, Double amount) {
		synchronized (accounts) {
			Double balance = getBalance(acct);
			Double updated = balance + amount;
			updateBalance(acct, updated);
			LOGGER.info("Credited {} to {}. New balance: {}", amount, acct, updated);
		}
	}
}
