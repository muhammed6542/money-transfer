package com.revolut.moneytransfer.factory;

import com.revolut.moneytransfer.dao.AccountDao;
import com.revolut.moneytransfer.dao.TransactionDao;
import com.revolut.moneytransfer.dao.UserDao;
import com.revolut.moneytransfer.dao.impl.AccountDaoImpl;
import com.revolut.moneytransfer.dao.impl.TransactionDaoImpl;
import com.revolut.moneytransfer.dao.impl.UserDaoImpl;

public class DaoFactory {

	private DaoFactory() {

	}

	public static AccountDao getAccountDao() {
		return new AccountDaoImpl();
	}

	public static UserDao getUserDao() {
		return new UserDaoImpl();
	}

	public static TransactionDao getTransactionDao() {
		return new TransactionDaoImpl();
	}

}
