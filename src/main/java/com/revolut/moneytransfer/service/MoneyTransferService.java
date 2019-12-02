package com.revolut.moneytransfer.service;

import javax.ws.rs.core.Response;

import com.revolut.moneytransfer.entity.Transaction;
import com.revolut.moneytransfer.exception.TechnicalException;

public interface MoneyTransferService {
	public void sendMoney(Transaction transaction) throws TechnicalException;

	public Response acceptMoney(Transaction transaction) throws TechnicalException;

}
