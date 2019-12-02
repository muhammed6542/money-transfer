package com.revolut.moneytransfer.test.dao;

import static junit.framework.TestCase.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.moneytransfer.entity.Transaction;
import com.revolut.moneytransfer.factory.DaoFactory;
import com.revolut.moneytransfer.util.Util;

public class TransactionDaoTest {

	private static Logger logger = LoggerFactory.getLogger(TransactionDaoTest.class);

	@Test
	public void testGetTransactionBySenderAccount() {
		long senderAccount = 257612334;
		List<Transaction> transactionList = DaoFactory.getTransactionDao().getTransactionBySenderAccount(senderAccount);
		assertTrue(transactionList != null && transactionList.size() >= 1);
	}

	@Test
	public void testGetAllTransaction() {
		List<Transaction> transactionList = DaoFactory.getTransactionDao().getAllTransaction();
		assertTrue(transactionList.size() > 1);
	}

	@Test
	public void testGetTransactionByReceiverAccount() {
		long receiverAccount = 252986710;
		List<Transaction> transactionList = DaoFactory.getTransactionDao()
				.getTransactionByReceiverAccount(receiverAccount);
		assertTrue(transactionList != null && transactionList.size() >= 1);
	}

	@Test
	public void testGetNotCompletedTransaction() {
		List<Transaction> transactionList = DaoFactory.getTransactionDao().getNotCompletedTransaction();
		assertTrue(transactionList != null && transactionList.size() > 1);
	}

	@Test
	public void testInsertTransaction() {
		String token = Util.createTokenWhenTransactionStart();
		Transaction transaction = new Transaction(252502143, 432345675, new Date(), null, new BigDecimal(234), "TRY",
				"TRY", "W", 0, token);
		DaoFactory.getTransactionDao().insertTransaction(transaction);

		Transaction afterInsert = DaoFactory.getTransactionDao().getTransactionWithToken(token);
		assertTrue(afterInsert.getAmount().compareTo(new BigDecimal(234)) == 0);
		assertTrue(afterInsert.getSenderAccount() == 252502143);
		assertTrue(afterInsert.getReceiverAccount() == 432345675);
		assertTrue(afterInsert.getSenderCurrencyCode() == "TRY");
		assertTrue(afterInsert.getReceiverCurrencyCode() == "TRY");

	}

	@Test
	public void testUpdateTransaction() {
		String token = Util.createTokenWhenTransactionStart();
		Transaction transaction = new Transaction(252502143, 432345675, new Date(), null, new BigDecimal(234), "TRY",
				"TRY", "W", 0, token);
		DaoFactory.getTransactionDao().insertTransaction(transaction);

		transaction.setEndDate(new Date());
		transaction.setStatu("A");
		transaction.setIsReverse(1);

		DaoFactory.getTransactionDao().updateTransaction(transaction);

		Transaction afterInsert = DaoFactory.getTransactionDao().getTransactionWithToken(token);
		assertTrue(afterInsert.getStatu().equals("A"));
		assertTrue(afterInsert.getIsReverse() == 1);

	}

}
