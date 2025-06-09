package com.debitservice.events;

import java.io.Serializable;

public class TransferRequested implements Serializable {

	private static final long serialVersionUID = -3142221704250967731L;

	private String transferId;

	private String fromAccount;

	private String toAccount;

	private Double amount;

	public TransferRequested() {
		super();
	}

	public TransferRequested(String transferId, String fromAccount, String toAccount, Double amount) {
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "TransferRequested [transferId=" + transferId + ", fromAccount=" + fromAccount + ", toAccount="
				+ toAccount + ", amount=" + amount + "]";
	}
}
