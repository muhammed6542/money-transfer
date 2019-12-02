package com.revolut.moneytransfer.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.moneytransfer.entity.Account;
import com.revolut.moneytransfer.entity.Transaction;
import com.revolut.moneytransfer.exception.TechnicalException;
import com.revolut.moneytransfer.factory.DaoFactory;
import com.revolut.moneytransfer.service.MoneyTransferService;

public class MoneyTransferServiceImpl implements MoneyTransferService {
	private static Logger logger = LoggerFactory.getLogger(MoneyTransferServiceImpl.class);

	@Override
	public Response acceptMoney(Transaction transaction) {
		try {
			Account account = addAmountToBalance(transaction.getReceiverAccount(), transaction.getAmount());
			logger.info("account is updating : " + account);
			DaoFactory.getAccountDao().updateAccount(account);
			return Response.status(Response.Status.OK).build();
		} catch (TechnicalException e) {
			logger.error("Error in acceptMoney service", e);
			return Response.status(Response.Status.NOT_FOUND).entity("Accept money didnt work").build();
		}
	}

	@Override
	public void sendMoney(Transaction transaction) {
		Response response = acceptMoney(transaction);
		if (Response.Status.OK.getStatusCode() == response.getStatus()) {
			sendMoneyWhenAcceptMoneyResponseOk(transaction);
			logger.info("transaction is updating : " + transaction);
		} else {
			if ("T".equals(transaction.getStatu())) {
				int isSucces = sendMoneyAfterFirstTridFail(transaction);
				if (isSucces == 0) {
					cancelTransactionAfterSecondTriedFail(transaction);
					logger.info("transaction is canceling , sender account is updating : " + transaction);
				}
			} else {
				sendMoneyAfterFirstTridFail(transaction);
				logger.info("transaction had an error , statu is updating to 'T'  " + transaction);
			}

		}

	}

	private void sendMoneyWhenAcceptMoneyResponseOk(Transaction transaction) {
		transaction.setStatu("A");
		transaction.setEndDate(new Date());
		DaoFactory.getTransactionDao().updateTransaction(transaction);
	}

	private void cancelTransactionAfterSecondTriedFail(Transaction transaction) {
		Account account = addAmountToBalance(transaction.getSenderAccount(), transaction.getAmount());
		DaoFactory.getAccountDao().updateAccount(account);
		transaction.setStatu("C");
		transaction.setIsReverse(1);
		transaction.setEndDate(new Date());
		DaoFactory.getTransactionDao().updateTransaction(transaction);
	}

	private int sendMoneyAfterFirstTridFail(Transaction transaction) {
		transaction.setStatu("T");
		return DaoFactory.getTransactionDao().updateTransaction(transaction);
	}

	private Account addAmountToBalance(long accountNumber, BigDecimal amount) {
		Account account = DaoFactory.getAccountDao().getAccountsByAccountNumber(accountNumber);
		if (account != null) {
			account.setBalance(account.getBalance().add(amount));
		}
		return account;
	}

}
