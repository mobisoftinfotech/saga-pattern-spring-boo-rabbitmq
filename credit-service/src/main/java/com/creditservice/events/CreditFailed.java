package com.creditservice.events;

import java.io.Serializable;

public class CreditFailed implements Serializable {

	private static final long serialVersionUID = 5303865388253276258L;

	private String transferId;

	private String reason;

	public CreditFailed() {
		super();
	}

	public CreditFailed(String transferId, String reason) {
		super();
		this.transferId = transferId;
		this.reason = reason;
	}

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "CreditFailed [transferId=" + transferId + ", reason=" + reason + "]";
	}

}
