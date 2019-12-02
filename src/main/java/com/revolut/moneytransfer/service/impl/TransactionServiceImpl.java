package com.revolut.moneytransfer.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.moneytransfer.entity.Account;
import com.revolut.moneytransfer.entity.Transaction;
import com.revolut.moneytransfer.exception.TechnicalException;
import com.revolut.moneytransfer.factory.DaoFactory;
import com.revolut.moneytransfer.factory.ServiceFactory;
import com.revolut.moneytransfer.service.TransactionService;
import com.revolut.moneytransfer.util.Util;

public class TransactionServiceImpl implements TransactionService {
	private static Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Override
	public int insertTransaction(Transaction transaction) {
		try {
			checkCurrencyCode(transaction.getSenderCurrencyCode(), transaction.getReceiverCurrencyCode());
			checkSenderAccountAmount(transaction.getSenderAccount(), transaction.getAmount());
			checkReceiverAccount(transaction.getReceiverAccount());
			transaction.setIsReverse(0);
			transaction.setStatu("W");
			transaction.setStartDate(new Date());
			transaction.setEndDate(null);
			transaction.setTransactionToken(getUniqueToken());
			DaoFactory.getTransactionDao().insertTransaction(transaction);
			logger.info("Send money transaction inserted : " + transaction);
			return ServiceFactory.getAccountService()
					.updateAccount(removeAmountToBalance(transaction.getSenderAccount(), transaction.getAmount()));
		} catch (TechnicalException e) {
			throw new TechnicalException("Error in insertTransaction service", e);
		}

	}

	public List<Transaction> getTransactionBySenderAccount(long senderAccount) {
		return DaoFactory.getTransactionDao().getTransactionBySenderAccount(senderAccount);
	}

	public List<Transaction> getAllTransaction() {

		return DaoFactory.getTransactionDao().getAllTransaction();
	}

	public List<Transaction> getTransactionByReceiverAccount(long receiverAccount) {

		return DaoFactory.getTransactionDao().getTransactionByReceiverAccount(receiverAccount);
	}

	public List<Transaction> getNotCompletedTransaction() {

		return DaoFactory.getTransactionDao().getNotCompletedTransaction();
	}

	public int updateTransaction(Transaction transaction) {

		return DaoFactory.getTransactionDao().updateTransaction(transaction);
	}

	@Override
	public boolean isTokenUsedBefore(String token) {
		List<Transaction> transaction = DaoFactory.getTransactionDao().isTokenUsedBefore(token);

		return transaction.size() > 0 ? true : false;
	}

	public String getUniqueToken() {
		String token = Util.createTokenWhenTransactionStart();
		while (isTokenUsedBefore(token)) {
			token = Util.createTokenWhenTransactionStart();
		}
		return token;
	}

	public void checkCurrencyCode(String senderCurrencyCode, String receiverCurrencyCode) {
		if (senderCurrencyCode == null || receiverCurrencyCode == null) {
			throw new TechnicalException("Please fill the currency code");
		}
		if (!senderCurrencyCode.equals(receiverCurrencyCode)) {
			throw new TechnicalException("Currency codes should be same");
		}
	}

	public void checkSenderAccountAmount(long senderAccount, BigDecimal amount) {
		Account account = ServiceFactory.getAccountService().getAccountsByAccountNumber(senderAccount);
		if (account == null) {
			logger.warn("Sender account didnt find ");
			throw new TechnicalException("Sender account didnt find ");
		}
		if (account.getBalance().compareTo(amount) < 0) {
			logger.warn("Sender account amount has not enough money");
			throw new TechnicalException("Sender account amount has not enough money");
		}
	}

	public void checkReceiverAccount(long receiverAccount) {
		Account account = ServiceFactory.getAccountService().getAccountsByAccountNumber(receiverAccount);
		if (account == null) {
			logger.warn("Receiver account didnt find ");
			throw new TechnicalException("Receiver account didnt find ");
		}
	}

	private Account removeAmountToBalance(long accountNumber, BigDecimal amount) {

		Account account = ServiceFactory.getAccountService().getAccountsByAccountNumber(accountNumber);
		if (account != null) {
			account.setBalance(account.getBalance().subtract(amount));
		}
		return account;
	}
}
