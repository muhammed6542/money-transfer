package com.revolut.moneytransfer.service;

import java.util.List;

import com.revolut.moneytransfer.entity.Transaction;
import com.revolut.moneytransfer.exception.TechnicalException;

public interface TransactionService {

	public List<Transaction> getTransactionBySenderAccount(long senderAccount) throws TechnicalException;

	public List<Transaction> getAllTransaction() throws TechnicalException;

	public List<Transaction> getTransactionByReceiverAccount(long receiverAccount) throws TechnicalException;

	public List<Transaction> getNotCompletedTransaction() throws TechnicalException;

	public int updateTransaction(Transaction transaction) throws TechnicalException;

	public int insertTransaction(Transaction transaction) throws TechnicalException;

	public boolean isTokenUsedBefore(String token) throws TechnicalException;

}
