package com.revolut.moneytransfer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.moneytransfer.dao.AccountDao;
import com.revolut.moneytransfer.entity.Account;
import com.revolut.moneytransfer.exception.TechnicalException;
import com.revolut.moneytransfer.factory.ConnectionFactory;

public class AccountDaoImpl implements AccountDao {
	private static Logger logger = LoggerFactory.getLogger(AccountDaoImpl.class);

	private final String SQL_INSERT_ACCOUNT = "INSERT INTO account (account_number,phone_number,balance,currency_code) VALUES (?,?,?, ?)";
	private final String SQL_UPDATE_ACCOUNT = "UPDATE account SET balance = ?, currency_code = ?  WHERE account_number = ? ";
	private final String SQL_DELETE_ACCOUNT = "DELETE FROM account WHERE account_number = ? ";
	private final String SQL_GET_ALL_ACCOUNTS = "SELECT * FROM account";
	private final String SQL_GET_ACCOUNTS_BY_ACCOUNT_NUMBER = "SELECT * FROM account WHERE account_number = ? ";
	private final String SQL_GET_ACCOUNTS_BY_PHONE_NUMBER = "SELECT * FROM account WHERE phone_number = ? ";

	@Override
	public int insertAccount(Account account) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = ConnectionFactory.getDBConnection();
			stmt = conn.prepareStatement(SQL_INSERT_ACCOUNT);
			stmt.setLong(1, account.getAccountNumber());
			stmt.setString(2, account.getPhoneNumber());
			stmt.setBigDecimal(3, account.getBalance());
			stmt.setString(4, account.getCurrencyCode());
			int isCreated = stmt.executeUpdate();
			conn.commit();
			if (isCreated == 0) {
				logger.error("Error in insertAccount dao , account is not created");
				throw new TechnicalException("Error in insertAccount dao , account is not created");
			}
			return isCreated;
		} catch (SQLException e) {
			logger.error("Error in insertAccount dao ", e);
			throw new TechnicalException("Error in insertAccount dao ", e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(stmt);
		}
	}

	@Override
	public int updateAccount(Account account) {
		Connection conn = null;
		PreparedStatement preStmt = null;

		try {
			conn = ConnectionFactory.getDBConnection();
			conn.setAutoCommit(false);
			preStmt = conn.prepareStatement(SQL_UPDATE_ACCOUNT);
			preStmt.setBigDecimal(1, account.getBalance());
			preStmt.setString(2, account.getCurrencyCode());
			preStmt.setLong(3, account.getAccountNumber());
			int isUpdated = preStmt.executeUpdate();
			conn.commit();
			if (isUpdated == 0) {
				logger.error("Error in updateAccount dao , account is not updated");
				throw new TechnicalException("Error in updateAccount dao , account is not updated");
			}

			return isUpdated;

		} catch (SQLException e) {
			logger.error("Error in updateAccount dao", e);
			throw new TechnicalException("Error in insertAccount dao ", e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(preStmt);
		}

	}

	@Override
	public int deleteAccount(long accountNumber) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		try {
			conn = ConnectionFactory.getDBConnection();
			preStmt = conn.prepareStatement(SQL_DELETE_ACCOUNT);
			preStmt.setLong(1, accountNumber);
			int isDeleted = preStmt.executeUpdate();
			conn.commit();
			if (isDeleted == 0) {
				logger.error("Error in deleteAccount dao , account is not deleted");
				throw new TechnicalException("Error in deleteAccount dao , account is not deleted");
			}
			return isDeleted;
		} catch (SQLException e) {
			logger.error("Error in deleteAccount dao", e);
			throw new TechnicalException("Error in deleteAccount dao ", e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(preStmt);
		}
	}

	@Override
	public List<Account> getAllAccounts() {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Account> accounts = new ArrayList<Account>();
		try {
			conn = ConnectionFactory.getDBConnection();
			stmt = conn.prepareStatement(SQL_GET_ALL_ACCOUNTS);
			rs = stmt.executeQuery();
			if (rs == null) {
				return null;
			}
			while (rs.next()) {
				Account account = new Account(rs.getLong("id"), rs.getLong("account_number"),
						rs.getString("phone_number"), rs.getBigDecimal("balance"), rs.getString("currency_code"));
				accounts.add(account);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Account lists : " + accounts);
			}
		} catch (SQLException e) {
			logger.error("Error in getAccounts dao", e);
			throw new TechnicalException("Error in getAccounts dao ", e);
		} finally {
			DbUtils.closeQuietly(conn, stmt, rs);
		}
		return accounts;

	}

	@Override
	public Account getAccountsByAccountNumber(long accountNumber) {

		Connection conn = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		Account account = new Account();
		try {
			conn = ConnectionFactory.getDBConnection();
			preStmt = conn.prepareStatement(SQL_GET_ACCOUNTS_BY_ACCOUNT_NUMBER);
			preStmt.setLong(1, accountNumber);
			rs = preStmt.executeQuery();

			while (rs.next()) {
				account = new Account(rs.getLong("id"), rs.getLong("account_number"), rs.getString("phone_number"),
						rs.getBigDecimal("balance"), rs.getString("currency_code"));
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Account lists : " + account);
			}
		} catch (SQLException e) {
			logger.error("Error in getAccountsByAccountNumber dao", e);
			throw new TechnicalException("Error in getAccountsByAccountNumber dao ", e);
		} finally {
			DbUtils.closeQuietly(conn, preStmt, rs);
		}
		return account;

	}

	@Override
	public List<Account> getAccountsByPhoneNumber(String phoneNumber) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Account> accounts = new ArrayList<Account>();
		try {
			conn = ConnectionFactory.getDBConnection();
			stmt = conn.prepareStatement(SQL_GET_ACCOUNTS_BY_PHONE_NUMBER);
			stmt.setString(1, phoneNumber);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Account account = new Account(rs.getLong("id"), rs.getLong("account_number"),
						rs.getString("phone_number"), rs.getBigDecimal("balance"), rs.getString("currency_code"));
				accounts.add(account);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Account lists : " + accounts);
			}
		} catch (SQLException e) {
			logger.error("Error in getAccountsByPhoneNumber dao", e);
			throw new TechnicalException("Error in getAccountsByPhoneNumber dao ", e);
		} finally {
			DbUtils.closeQuietly(conn, stmt, rs);
		}
		return accounts;

	}

}
