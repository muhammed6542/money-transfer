package com.revolut.moneytransfer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	@JsonIgnore
	private long userNo;
	@JsonProperty(required = true)
	private String name;
	@JsonProperty(required = true)
	private String surname;
	@JsonProperty(required = true)
	private String phoneNumber;
	@JsonProperty(required = true)
	private String emailAdress;

	public User() {

	}

	public User(long userNo, String name, String surname, String phoneNumber, String emailAdress) {
		this.userNo = userNo;
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.emailAdress = emailAdress;
	}

	public User(String name, String surname, String phoneNumber, String emailAdress) {
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.emailAdress = emailAdress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	public long getUserNo() {
		return userNo;
	}

	public void setUserNo(long userNo) {
		this.userNo = userNo;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userNo=");
		builder.append(userNo);
		builder.append(", name=");
		builder.append(name);
		builder.append(", surname=");
		builder.append(surname);
		builder.append(", phoneNumber=");
		builder.append(phoneNumber);
		builder.append(", emailAdress=");
		builder.append(emailAdress);
		builder.append("]");
		return builder.toString();
	}
}
