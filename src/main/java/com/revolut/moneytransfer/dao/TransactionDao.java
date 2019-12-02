package com.revolut.moneytransfer.dao;

import java.util.List;

import com.revolut.moneytransfer.entity.Transaction;
import com.revolut.moneytransfer.exception.TechnicalException;

public interface TransactionDao {

	public List<Transaction> getTransactionBySenderAccount(long senderAccount) ;

	public List<Transaction> getAllTransaction();

	public List<Transaction> getTransactionByReceiverAccount(long receiverAccount) ;

	public List<Transaction> getNotCompletedTransaction() ;

	public int insertTransaction(Transaction transaction) ;

	public int updateTransaction(Transaction transaction) ;

	public List<Transaction> isTokenUsedBefore(String token) ;

	public Transaction getTransactionWithToken(String token) ;
}
