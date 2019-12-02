package com.revolut.moneytransfer.test.dao;

import static junit.framework.TestCase.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.moneytransfer.entity.Account;
import com.revolut.moneytransfer.exception.TechnicalException;
import com.revolut.moneytransfer.factory.DaoFactory;

public class AccountDaoTest {
 
	private static Logger logger = LoggerFactory.getLogger(AccountDaoTest.class);

	@Test
	public void testGetAllAccounts() throws TechnicalException {
		logger.info("allAccounts  dao test started");
		List<Account> allAccounts = DaoFactory.getAccountDao().getAllAccounts();
		assertTrue(allAccounts.size() > 1);
	}

	@Test
	public void testAccountsByAccountNumber() throws TechnicalException {
		logger.info("accountByAccountNumber dao test started");
		Account account = DaoFactory.getAccountDao().getAccountsByAccountNumber(232212343);
		assertTrue(account.getCurrencyCode().equals("GBP"));
	}

	@Test
	public void testAccountsByPhoneNumber() throws TechnicalException {
		logger.info("accountByPhoneNumber  dao test started");
		List<Account> account = DaoFactory.getAccountDao().getAccountsByPhoneNumber("05439004050");
		assertTrue(account.size() > 0);
	}

	@Test
	public void testInsertAccount() throws URISyntaxException, ClientProtocolException, IOException {
		logger.info("inserAccount dao test started");

		Account account = new Account(212334557, "05404321245", new BigDecimal(20), "USD");
		DaoFactory.getAccountDao().insertAccount(account);

		Account afterInsert = DaoFactory.getAccountDao().getAccountsByAccountNumber(212334557);

		assertTrue(afterInsert.getAccountNumber() == 212334557);
		assertTrue(afterInsert.getBalance().compareTo(new BigDecimal(20)) == 0);
		assertTrue(afterInsert.getCurrencyCode().equals("USD"));
		assertTrue(afterInsert.getPhoneNumber().equals("05404321245"));
	}

	@Test
	public void testUpdateAccount() throws URISyntaxException, ClientProtocolException, IOException {
		logger.info("updateAccount  dao test started");
//		Account account = new Account(212334556, "05423456789", new BigDecimal(20), "USD");
		Account account = DaoFactory.getAccountDao().getAccountsByAccountNumber(252502102);
		account.setCurrencyCode("JPY");
		account.setBalance(new BigDecimal(40));
		DaoFactory.getAccountDao().updateAccount(account);

		Account afterUpdate = DaoFactory.getAccountDao().getAccountsByAccountNumber(252502102);
		assertTrue(afterUpdate.getBalance().compareTo(new BigDecimal(40)) == 0);
		assertTrue(afterUpdate.getCurrencyCode().equals("JPY"));
	}

	@Test
	public void testDeleteAccount() throws URISyntaxException, ClientProtocolException, IOException {
		logger.info("deleteAccount  dao test started");
//		Account account = new Account(212334556, "05423456789", new BigDecimal(20), "USD");
		int isDeleted = DaoFactory.getAccountDao().deleteAccount(232212343);
		assertTrue(isDeleted == 1);
	}

}
