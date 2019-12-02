package com.revolut.moneytransfer.factory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.h2.tools.RunScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionFactory {
	private static Logger logger = LoggerFactory.getLogger(ConnectionFactory.class);
	private static final String DATABASE_DRIVER = "org.h2.Driver";
	private static final String DATABASE_CONNECTION = "jdbc:h2:tcp://localhost/~/moneytransfer";
	private static final String DATABASE_USER = "sa";
	private static final String DATABASE_PASSWORD = "";

	public static void createTestData() {
		Connection conn = null;

		try {
			conn = getDBConnection();
			RunScript.execute(conn, new FileReader("src/main/resources/money_transfer.sql"));
		} catch (SQLException e) {
			logger.error("Error in createTestData", e);
			throw new RuntimeException(e);
		} catch (FileNotFoundException e) {
			logger.error("Error in createTestData", e);
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	public static Connection getDBConnection() {
 		Connection conn = null;
		try {
			Class.forName(DATABASE_DRIVER);
			conn = DriverManager.getConnection(DATABASE_CONNECTION, DATABASE_USER, DATABASE_PASSWORD);
			return conn;
		} catch (SQLException e) {
			logger.error("Error in getDBConnection", e);
		} catch (ClassNotFoundException e) {
			logger.error("Error in getDBConnection", e);
		}

		return conn;
	}
}