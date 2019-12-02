package com.revolut.moneytransfer.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {

	@JsonIgnore
	private long accountNo;

	@JsonProperty(required = true)
	private long accountNumber;

	@JsonProperty(required = true)
	private String phoneNumber;

	@JsonProperty(required = true)
	private BigDecimal balance;

	@JsonProperty(required = true)
	private String currencyCode;

	public Account() {

	}

	public Account(long accountNo, long accountNumber, String phoneNumber, BigDecimal balance, String currencyCode) {
		this.accountNo = accountNo;
		this.accountNumber = accountNumber;
		this.phoneNumber = phoneNumber;
		this.balance = balance;
		this.currencyCode = currencyCode;
	}

	public Account(long accountNumber, String phoneNumber, BigDecimal balance, String currencyCode) {
		this.accountNumber = accountNumber;
		this.phoneNumber = phoneNumber;
		this.balance = balance;
		this.currencyCode = currencyCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [accountNo=");
		builder.append(accountNo);
		builder.append(", accountNumber=");
		builder.append(accountNumber);
		builder.append(", phoneNumber=");
		builder.append(phoneNumber);
		builder.append(", balance=");
		builder.append(balance);
		builder.append(", currencyCode=");
		builder.append(currencyCode);
		builder.append("]");
		return builder.toString();
	}

}
