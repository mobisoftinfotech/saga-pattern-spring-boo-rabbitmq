package com.debitservice.events;

import java.io.Serializable;

public class DebitFailed implements Serializable {

	private static final long serialVersionUID = 860942756893122512L;

	private String transferId;

	private String reason;

	public DebitFailed() {
		super();
	}

	public DebitFailed(String transferId, String reason) {
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
		return "DebitFailed [transferId=" + transferId + ", reason=" + reason + "]";
	}

}
