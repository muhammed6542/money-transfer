package com.revolut.moneytransfer.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.moneytransfer.entity.User;
import com.revolut.moneytransfer.exception.TechnicalException;
import com.revolut.moneytransfer.factory.DaoFactory;
import com.revolut.moneytransfer.service.UserService;

public class UserServiceImpl implements UserService {

	public List<User> getAllUser() {
		return DaoFactory.getUserDao().getAllUser();
	}

	public User getUserByPhoneNumber(String phoneNumber) {
		return DaoFactory.getUserDao().getUserByPhoneNumber(phoneNumber);
	}

	public int insertUser(User user) {
		return DaoFactory.getUserDao().insertUser(user);
	}

	public int updateUser(User user) {
		return DaoFactory.getUserDao().updateUser(user);
	}

	public int deleteUser(String phoneNumber) {
		return DaoFactory.getUserDao().deleteUser(phoneNumber);
	}

}
