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

import com.revolut.moneytransfer.dao.UserDao;
import com.revolut.moneytransfer.entity.User;
import com.revolut.moneytransfer.exception.TechnicalException;
import com.revolut.moneytransfer.factory.ConnectionFactory;

public class UserDaoImpl implements UserDao {
	private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private final String SQL_GET_ALL_USER = "SELECT * FROM User";
	private final String SQL_GET_USER_BY_PHONE_NUMBER = "SELECT * FROM user WHERE phone_number = ? ";
	private final String SQL_INSERT_USER = "INSERT INTO user (name,surname,email_address,phone_number) VALUES (?,?,?, ?)";
	private final String SQL_DELETE_USER = "DELETE FROM user WHERE phone_number = ? ";
	private final String SQL_UPDATE_USER = "UPDATE user SET name = ?, surname = ? , email_address=? WHERE phone_number = ? ";

	@Override
	public List<User> getAllUser() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();
		try {
			conn = ConnectionFactory.getDBConnection();
			stmt = conn.prepareStatement(SQL_GET_ALL_USER);
			rs = stmt.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getLong("id"), rs.getString("name"), rs.getString("surname"),
						rs.getString("phone_number"), rs.getString("email_address"));
				userList.add(user);
			}

		} catch (SQLException e) {
			logger.error("Error in getAllUser dao", e);
			throw new TechnicalException("Error in getAllUser dao ", e);
		} finally {
			DbUtils.closeQuietly(conn, stmt, rs);
		}
		return userList;

	}

	@Override
	public User getUserByPhoneNumber(String phoneNumber) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = ConnectionFactory.getDBConnection();
			stmt = conn.prepareStatement(SQL_GET_USER_BY_PHONE_NUMBER);
			stmt.setString(1, phoneNumber);
			rs = stmt.executeQuery();
			while (rs.next()) {
				user = new User(rs.getLong("id"), rs.getString("name"), rs.getString("surname"),
						rs.getString("phone_number"), rs.getString("email_address"));
			}

		} catch (SQLException e) {

			logger.error("Error in getUserByPhoneNumber dao", e);
			throw new TechnicalException("Error in getUserByPhoneNumber dao ", e);
		} finally {
			DbUtils.closeQuietly(conn, stmt, rs);
		}
		return user;

	}

	@Override
	public int insertUser(User user) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		try {
			conn = ConnectionFactory.getDBConnection();
			preStmt = conn.prepareStatement(SQL_INSERT_USER);
			preStmt.setString(1, user.getName());
			preStmt.setString(2, user.getSurname());
			preStmt.setString(3, user.getEmailAdress());
			preStmt.setString(4, user.getPhoneNumber());
			int isCreated = preStmt.executeUpdate();
			conn.commit();

			if (isCreated == 0) {
				logger.error("Error in insertUser dao , user didnt create");
				throw new TechnicalException("Error in insertUser dao , user didnt create");
			}
			return isCreated;

		} catch (SQLException e) {

			logger.error("Error in insertUser dao", e);
			throw new TechnicalException("Error in insertUser dao ", e);
		} finally {

			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(preStmt);
		}
	}

	public int updateUser(User user) {
		Connection conn = null;
		PreparedStatement preStmt = null;

		try {
			conn = ConnectionFactory.getDBConnection();
			preStmt = conn.prepareStatement(SQL_UPDATE_USER);
			preStmt.setString(1, user.getName());
			preStmt.setString(2, user.getSurname());
			preStmt.setString(3, user.getEmailAdress());
			preStmt.setString(4, user.getPhoneNumber());
			int isUpdated = preStmt.executeUpdate();
			conn.commit();

			if (isUpdated == 0) {
				logger.error("Error in updateUser dao , user didnt update");
				throw new TechnicalException("Error in updateUser dao , user didnt update");
			}
			return isUpdated;

		} catch (SQLException e) {

			logger.error("Error in updateUser dao", e);
			throw new TechnicalException("Error in updateUser dao ", e);
		} finally {

			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(preStmt);
		}

	}

	@Override
	public int deleteUser(String phoneNumber) {
		Connection conn = null;
		PreparedStatement preStmt = null;
		try {
			conn = ConnectionFactory.getDBConnection();
			preStmt = conn.prepareStatement(SQL_DELETE_USER);
			preStmt.setString(1, phoneNumber);
			int isDeleted = preStmt.executeUpdate();
			conn.commit();

			if (isDeleted == 0) {
				logger.error("Error in deleteUser dao , user didnt delete");
				throw new TechnicalException("Error in deleteUser dao , user didnt delete");
			}
			return isDeleted;

		} catch (SQLException e) {
			logger.error("Error in deleteUser dao", e);
			throw new TechnicalException("Error in deleteUser dao ", e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(preStmt);
		}
	}

}
