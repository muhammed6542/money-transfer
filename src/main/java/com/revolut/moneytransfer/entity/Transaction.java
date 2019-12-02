package com.revolut.moneytransfer.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {

	@JsonIgnore
	private Long transactionNo;
	@JsonProperty(required = true)
	private long senderAccount;
	@JsonProperty(required = true)
	private long receiverAccount;
	@JsonProperty(required = true)
	private Date startDate;
	@JsonProperty(required = true)
	private Date endDate;
	@JsonProperty(required = true)
	private BigDecimal amount;
	@JsonProperty(required = true)
	private String senderCurrencyCode;
	@JsonProperty(required = true)
	private String receiverCurrencyCode;
	@JsonProperty(required = true)
	private String statu;// Action Cancel Waiting
	@JsonProperty(required = true)
	private int isReverse;// 0 1
	@JsonProperty(required = true)
	private String transactionToken;

	public Transaction() {

	}

	public Transaction(long transactionNo, long senderAccount, long receiverAccount, Date startDate, Date endDate,
			BigDecimal amount, String senderCurrencyCode, String receiverCurrencyCode, String statu, int isReverse,
			String transactionToken) {
		this.transactionNo = transactionNo;
		this.senderAccount = senderAccount;
		this.receiverAccount = receiverAccount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.senderCurrencyCode = senderCurrencyCode;
		this.receiverCurrencyCode = receiverCurrencyCode;
		this.statu = statu;
		this.isReverse = isReverse;
		this.transactionToken = transactionToken;
	}

	public Transaction(long senderAccount, long receiverAccount, Date startDate, Date endDate, BigDecimal amount,
			String senderCurrencyCode, String receiverCurrencyCode, String statu, int isReverse,
			String transactionToken) {
		this.senderAccount = senderAccount;
		this.receiverAccount = receiverAccount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.senderCurrencyCode = senderCurrencyCode;
		this.receiverCurrencyCode = receiverCurrencyCode;
		this.statu = statu;
		this.isReverse = isReverse;
		this.transactionToken = transactionToken;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public long getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(long senderAccount) {
		this.senderAccount = senderAccount;
	}

	public long getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(long receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	public Long getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(Long transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getSenderCurrencyCode() {
		return senderCurrencyCode;
	}

	public void setSenderCurrencyCode(String senderCurrencyCode) {
		this.senderCurrencyCode = senderCurrencyCode;
	}

	public String getReceiverCurrencyCode() {
		return receiverCurrencyCode;
	}

	public void setReceiverCurrencyCode(String receiverCurrencyCode) {
		this.receiverCurrencyCode = receiverCurrencyCode;
	}

	public int getIsReverse() {
		return isReverse;
	}

	public void setIsReverse(int isReverse) {
		this.isReverse = isReverse;
	}

	public String getTransactionToken() {
		return transactionToken;
	}

	public void setTransactionToken(String transactionToken) {
		this.transactionToken = transactionToken;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transaction [transactionNo=");
		builder.append(transactionNo);
		builder.append(", senderAccount=");
		builder.append(senderAccount);
		builder.append(", receiverAccount=");
		builder.append(receiverAccount);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", senderCurrencyCode=");
		builder.append(senderCurrencyCode);
		builder.append(", receiverCurrencyCode=");
		builder.append(receiverCurrencyCode);
		builder.append(", statu=");
		builder.append(statu);
		builder.append(", isReverse=");
		builder.append(isReverse);
		builder.append(", transactionToken=");
		builder.append(transactionToken);
		builder.append("]");
		return builder.toString();
	}
}
