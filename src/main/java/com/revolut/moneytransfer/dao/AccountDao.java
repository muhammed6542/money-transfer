package com.revolut.moneytransfer.dao;

import java.util.List;

import com.revolut.moneytransfer.entity.Account;
import com.revolut.moneytransfer.exception.TechnicalException;

public interface AccountDao {
	public List<Account> getAllAccounts();

	public Account getAccountsByAccountNumber(long accountNumber);

	public List<Account> getAccountsByPhoneNumber(String phoneNumber);

	public int insertAccount(Account account);

	public int updateAccount(Account account);

	public int deleteAccount(long accountNumber);

}
