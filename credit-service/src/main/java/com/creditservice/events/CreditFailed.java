package com.creditservice.events;

import java.io.Serializable;

public class CreditFailed implements Serializable {

	private static final long serialVersionUID = 5303865388253276258L;

	private String transferId;

	private String reason;

	private double refundAmount;

	private String refundAccount;

	public CreditFailed() {
		super();
	}

	public CreditFailed(String transferId, String reason, double refundAmount, String refundAccount) {
		super();
		this.transferId = transferId;
		this.reason = reason;
		this.refundAmount = refundAmount;
		this.refundAccount = refundAccount;
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

	public double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getRefundAccount() {
		return refundAccount;
	}

	public void setRefundAccount(String refundAccount) {
		this.refundAccount = refundAccount;
	}

	@Override
	public String toString() {
		return "CreditFailed [transferId=" + transferId + ", reason=" + reason + ", refundAmount=" + refundAmount
				+ ", refundAccount=" + refundAccount + "]";
	}

}
