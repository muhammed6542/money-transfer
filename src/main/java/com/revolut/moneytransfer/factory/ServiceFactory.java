package com.revolut.moneytransfer.factory;

import com.revolut.moneytransfer.service.AccountService;
import com.revolut.moneytransfer.service.MoneyTransferService;
import com.revolut.moneytransfer.service.TransactionService;
import com.revolut.moneytransfer.service.UserService;
import com.revolut.moneytransfer.service.impl.AccountServiceImpl;
import com.revolut.moneytransfer.service.impl.MoneyTransferServiceImpl;
import com.revolut.moneytransfer.service.impl.TransactionServiceImpl;
import com.revolut.moneytransfer.service.impl.UserServiceImpl;

public class ServiceFactory {
	private ServiceFactory() {
	}

	public static AccountService getAccountService() {
		return new AccountServiceImpl();
	}

	public static UserService getUserService() {
		return new UserServiceImpl();
	}

	public static TransactionService getTransactionService() {
		return new TransactionServiceImpl();
	}

	public static MoneyTransferService getSendMoneyService() {
		return new MoneyTransferServiceImpl();
	}
}
