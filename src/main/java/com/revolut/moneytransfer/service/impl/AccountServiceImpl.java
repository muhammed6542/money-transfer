package com.revolut.moneytransfer.service.impl;

import java.util.List;

import com.revolut.moneytransfer.entity.Account;
import com.revolut.moneytransfer.factory.DaoFactory;
import com.revolut.moneytransfer.service.AccountService;

public class AccountServiceImpl implements AccountService {

 
	public List<Account> getAllAccounts() {
		return DaoFactory.getAccountDao().getAllAccounts();
	}

	public int insertAccount(Account account) {
		return DaoFactory.getAccountDao().insertAccount(account);
	}

	public int updateAccount(Account account) {
		return DaoFactory.getAccountDao().updateAccount(account);
	}

	public int deleteAccount(long accountNumber) {
		return DaoFactory.getAccountDao().deleteAccount(accountNumber);
	}

	public Account getAccountsByAccountNumber(long accountNumber) {
		return DaoFactory.getAccountDao().getAccountsByAccountNumber(accountNumber);

	}

	public List<Account> getAccountsByPhoneNumber(String phoneNumber) {
		return DaoFactory.getAccountDao().getAccountsByPhoneNumber(phoneNumber);
	}

}
