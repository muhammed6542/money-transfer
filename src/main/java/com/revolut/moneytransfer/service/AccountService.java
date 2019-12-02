package com.revolut.moneytransfer.service;

import java.util.List;

import com.revolut.moneytransfer.entity.Account;
import com.revolut.moneytransfer.exception.TechnicalException;

public interface AccountService {

	public List<Account> getAllAccounts() throws TechnicalException;

	public int insertAccount(Account account) throws TechnicalException;

	public int updateAccount(Account account) throws TechnicalException;

	public int deleteAccount(long accountNumber) throws TechnicalException;

	public Account getAccountsByAccountNumber(long accountNumber) throws TechnicalException;

	public List<Account> getAccountsByPhoneNumber(String phoneNumber) throws TechnicalException;

}
