package com.revolut.moneytransfer.test.service;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.moneytransfer.entity.Account;
import com.revolut.moneytransfer.entity.Transaction;
import com.revolut.moneytransfer.factory.ServiceFactory;
import com.revolut.moneytransfer.util.Util;

public class MoneyTransferServiceTest {

	private static Logger logger = LoggerFactory.getLogger(MoneyTransferServiceTest.class);

	@Test
	public void testSendMoneyOkey() throws URISyntaxException, ClientProtocolException, IOException {
		Account account = ServiceFactory.getAccountService().getAccountsByAccountNumber(252502143);

		Transaction transaction = new Transaction(252502143, 432345675, new Date(), null, new BigDecimal(2), "TRY",
				"TRY", "W", 0, Util.createTokenWhenTransactionStart());
		ServiceFactory.getTransactionService().insertTransaction(transaction);

		ServiceFactory.getSendMoneyService().sendMoney(transaction);
		Account accountAfterMoneySent = ServiceFactory.getAccountService().getAccountsByAccountNumber(252502143);
		BigDecimal accountBalance = account.getBalance().subtract(new BigDecimal(2));
		assertTrue(accountBalance.compareTo(accountAfterMoneySent.getBalance()) == 0);
	}

	@Test
	public void testAcceptMoney() throws URISyntaxException, ClientProtocolException, IOException {
		Account account = ServiceFactory.getAccountService().getAccountsByAccountNumber(432345675);

		Transaction transaction = new Transaction(252502143, 432345675, new Date(), null, new BigDecimal(2), "TRY",
				"TRY", "W", 0, Util.createTokenWhenTransactionStart());
		ServiceFactory.getTransactionService().insertTransaction(transaction);

		ServiceFactory.getSendMoneyService().acceptMoney(transaction);
		Account accountAfterMoneySent = ServiceFactory.getAccountService().getAccountsByAccountNumber(432345675);
		BigDecimal accountBalance = account.getBalance().add(new BigDecimal(2));
		assertTrue(accountBalance.compareTo(accountAfterMoneySent.getBalance()) == 0);

	}

}
