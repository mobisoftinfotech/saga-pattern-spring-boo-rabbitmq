package com.debitservice.events;

import java.io.Serializable;

public class DebitCompleted implements Serializable {

	private static final long serialVersionUID = -5857044410247537900L;

	private String transferId;

	private String fromAccount;

	private String toAccount;

	private double amount;

	public DebitCompleted() {
		super();
	}

	public DebitCompleted(String transferId, String fromAccount, String toAccount, double amount) {
		super();
		this.transferId = transferId;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
	}

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "DebitCompleted [transferId=" + transferId + ", fromAccount=" + fromAccount + ", toAccount=" + toAccount
				+ ", amount=" + amount + "]";
	}

}
