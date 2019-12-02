package com.revolut.moneytransfer.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.moneytransfer.dao.TransactionDao;
import com.revolut.moneytransfer.entity.Transaction;
import com.revolut.moneytransfer.exception.TechnicalException;
import com.revolut.moneytransfer.factory.ConnectionFactory;

public class TransactionDaoImpl implements TransactionDao {
	private static Logger logger = LoggerFactory.getLogger(TransactionDaoImpl.class);

	private final String SQL_INSERT_TRANSACTION = "INSERT INTO transaction (start_date,end_date,amount,sender_account,receiver_account,sender_currency_code,receiver_currency_code,statu,is_reverse,transaction_token) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private final String SQL_GET_TRANSACTION_BY_SENDER_ACCOUNT = "SELECT * FROM transaction WHERE sender_account = ? ";
	private final String SQL_GET_ALL_TRANSACTION = "SELECT * FROM transaction";
	private final String SQL_GET_TRANSACTION_BY_RECEIVER_ACCOUNT = "SELECT * FROM transaction WHERE receiver_account = ? ";
	private final String SQL_GET_NOT_COMPLETED_TRANSACTION = "SELECT * FROM transaction WHERE statu ='W' or statu = 'T'";
	private final String SQL_UPDATE_TRANSACTION = "UPDATE transaction SET end_date = ?, statu = ? , is_reverse = ?  WHERE transaction_token = ? ";
	private final String SQL_IS_TOKEN_USED_BEFORE = "SELECT * FROM transaction where transaction_token= ? ";
	private final String SQL_GET_TRANSACTION_WITH_TOKEN_ = "SELECT * FROM transaction WHERE transaction_token = ? ";

	@Override
	public int insertTransaction(Transaction transaction) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		try {
			conn = ConnectionFactory.getDBConnection();
			preStmt = conn.prepareStatement(SQL_INSERT_TRANSACTION);
			preStmt.setDate(1, new Date(transaction.getStartDate().getTime()));
			preStmt.setDate(2, new Date(transaction.getStartDate().getTime()));
			preStmt.setBigDecimal(3, transaction.getAmount());
			preStmt.setLong(4, transaction.getSenderAccount());
			preStmt.setLong(5, transaction.getReceiverAccount());
			preStmt.setString(6, transaction.getSenderCurrencyCode());
			preStmt.setString(7, transaction.getReceiverCurrencyCode());
			preStmt.setString(8, transaction.getStatu());
			preStmt.setInt(9, transaction.getIsReverse());
			preStmt.setString(10, transaction.getTransactionToken());

			int isCreated = preStmt.executeUpdate();
			conn.commit();

			if (isCreated == 0) {
				logger.error("Error in insertTransaction dao , transaction is not created");
				throw new TechnicalException("Error in insertTransaction dao , transaction is not created");
			}
			return isCreated;
		} catch (SQLException e) {
			logger.error("Error in insertTransaction dao", e);
			throw new TechnicalException("Error in insertTransaction dao ", e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(preStmt);
		}

	}

	@Override
	public List<Transaction> getTransactionBySenderAccount(long senderAccount) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			conn = ConnectionFactory.getDBConnection();
			stmt = conn.prepareStatement(SQL_GET_TRANSACTION_BY_SENDER_ACCOUNT);
			stmt.setLong(1, senderAccount);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Transaction transaction = new Transaction(rs.getLong("transaction_no"), rs.getLong("sender_account"),
						rs.getLong("receiver_account"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getBigDecimal("amount"), rs.getString("sender_currency_code"),
						rs.getString("receiver_currency_code"), rs.getString("statu"), rs.getInt("is_reverse"),
						rs.getString("transaction_token"));
				transactions.add(transaction);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Transaction lists : " + transactions);
			}
		} catch (SQLException e) {

			logger.error("Error in getTransactionBySenderAccount dao", e);
			throw new TechnicalException("Error in getTransactionBySenderAccount dao ", e);
		} finally {
			DbUtils.closeQuietly(conn, stmt, rs);
		}
		return transactions;
	}

	@Override
	public List<Transaction> getAllTransaction() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			conn = ConnectionFactory.getDBConnection();
			stmt = conn.prepareStatement(SQL_GET_ALL_TRANSACTION);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Transaction transaction = new Transaction(rs.getLong("transaction_no"), rs.getLong("sender_account"),
						rs.getLong("receiver_account"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getBigDecimal("amount"), rs.getString("sender_currency_code"),
						rs.getString("receiver_currency_code"), rs.getString("statu"), rs.getInt("is_reverse"),
						rs.getString("transaction_token"));
				transactions.add(transaction);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Transaction lists : " + transactions);
			}

		} catch (SQLException e) {

			logger.error("Error in getAllTransaction dao", e);
			throw new TechnicalException("Error in getAllTransaction dao ", e);
		} finally {
			DbUtils.closeQuietly(conn, stmt, rs);
		}
		return transactions;
	}

	@Override
	public List<Transaction> getTransactionByReceiverAccount(long receiverAccount) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			conn = ConnectionFactory.getDBConnection();
			stmt = conn.prepareStatement(SQL_GET_TRANSACTION_BY_RECEIVER_ACCOUNT);
			stmt.setLong(1, receiverAccount);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Transaction transaction = new Transaction(rs.getLong("transaction_no"), rs.getLong("sender_account"),
						rs.getLong("receiver_account"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getBigDecimal("amount"), rs.getString("sender_currency_code"),
						rs.getString("receiver_currency_code"), rs.getString("statu"), rs.getInt("is_reverse"),
						rs.getString("transaction_token"));
				transactions.add(transaction);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Transaction lists : " + transactions);
			}
		} catch (SQLException e) {
			logger.error("Error in getTransactionByReceiverAccount dao", e);
			throw new TechnicalException("Error in getTransactionByReceiverAccount dao ", e);
		} finally {
			DbUtils.closeQuietly(conn, stmt, rs);
		}
		return transactions;
	}

	@Override
	public List<Transaction> getNotCompletedTransaction() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			conn = ConnectionFactory.getDBConnection();
			stmt = conn.prepareStatement(SQL_GET_NOT_COMPLETED_TRANSACTION);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Transaction transaction = new Transaction(rs.getLong("transaction_no"), rs.getLong("sender_account"),
						rs.getLong("receiver_account"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getBigDecimal("amount"), rs.getString("sender_currency_code"),
						rs.getString("receiver_currency_code"), rs.getString("statu"), rs.getInt("is_reverse"),
						rs.getString("transaction_token"));
				transactions.add(transaction);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Transaction lists : " + transactions);
			}

		} catch (SQLException e) {
			logger.error("Error in getNotCompletedTransaction dao", e);
			throw new TechnicalException("Error in getNotCompletedTransaction dao ", e);
		} finally {
			DbUtils.closeQuietly(conn, stmt, rs);
		}
		return transactions;
	}

	@Override
	public int updateTransaction(Transaction transaction) {
		Connection conn = null;
		PreparedStatement preStmt = null;

		try {
			conn = ConnectionFactory.getDBConnection();
			conn.setAutoCommit(false);
			preStmt = conn.prepareStatement(SQL_UPDATE_TRANSACTION);
			preStmt.setDate(1, new Date(transaction.getEndDate().getTime()));
			preStmt.setString(2, transaction.getStatu());
			preStmt.setInt(3, transaction.getIsReverse());
			preStmt.setString(4, transaction.getTransactionToken());
			int isUpdated = preStmt.executeUpdate();
			if (isUpdated == 0) {
				logger.error("Error in updateTransaction dao , transaction has not been  updated");
				throw new TechnicalException("Error in updateTransaction dao , transaction has not been  updated");
			}
			conn.commit();
			return isUpdated;
		} catch (SQLException e) {
			logger.error("Error in updateTransaction dao", e);
			throw new TechnicalException("Error in updateTransaction dao ", e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(preStmt);
		}

	}

	@Override
	public List<Transaction> isTokenUsedBefore(String token) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			conn = ConnectionFactory.getDBConnection();
			stmt = conn.prepareStatement(SQL_IS_TOKEN_USED_BEFORE);
			stmt.setString(1, token);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Transaction transaction = new Transaction(rs.getLong("transaction_no"), rs.getLong("sender_account"),
						rs.getLong("receiver_account"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getBigDecimal("amount"), rs.getString("sender_currency_code"),
						rs.getString("receiver_currency_code"), rs.getString("statu"), rs.getInt("is_reverse"),
						rs.getString("transaction_token"));
				transactions.add(transaction);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Transaction lists : " + transactions);
			}

		} catch (SQLException e) {

			logger.error("Error in isTokenUsedBefore dao", e);
			throw new TechnicalException("Error in isTokenUsedBefore dao ", e);
		} finally {
			DbUtils.closeQuietly(conn, stmt, rs);
		}
		return transactions;

	}

	@Override
	public Transaction getTransactionWithToken(String token) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Transaction transaction = null;
		try {
			conn = ConnectionFactory.getDBConnection();
			stmt = conn.prepareStatement(SQL_GET_TRANSACTION_WITH_TOKEN_);
			stmt.setString(1, token);
			rs = stmt.executeQuery();

			while (rs.next()) {
				transaction = new Transaction(rs.getLong("transaction_no"), rs.getLong("sender_account"),
						rs.getLong("receiver_account"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getBigDecimal("amount"), rs.getString("sender_currency_code"),
						rs.getString("receiver_currency_code"), rs.getString("statu"), rs.getInt("is_reverse"),
						rs.getString("transaction_token"));
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Transaction lists : " + transaction);
			}
		} catch (SQLException e) {

			logger.error("Error in getTransactionWithToken dao", e);
			throw new TechnicalException("Error in getTransactionWithToken dao ", e);
		} finally {
			DbUtils.closeQuietly(conn, stmt, rs);
		}
		return transaction;

	}
}
