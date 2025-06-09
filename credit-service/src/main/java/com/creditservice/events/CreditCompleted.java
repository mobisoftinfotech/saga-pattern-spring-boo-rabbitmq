package com.creditservice.events;

import java.io.Serializable;

public class CreditCompleted implements Serializable {

	private static final long serialVersionUID = 1254361688491050696L;

	private String transferId;

	private String toAccount;

	private Double amount;

	public CreditCompleted() {
		super();
	}

	public CreditCompleted(String transferId, String toAccount, Double amount) {
		super();
		this.transferId = transferId;
		this.toAccount = toAccount;
		this.amount = amount;
	}

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "CreditCompleted [transferId=" + transferId + ", toAccount=" + toAccount + ", amount=" + amount + "]";
	}

}
