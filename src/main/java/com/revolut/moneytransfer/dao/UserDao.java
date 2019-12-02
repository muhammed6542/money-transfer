package com.revolut.moneytransfer.dao;

import java.util.List;

import com.revolut.moneytransfer.entity.User;
import com.revolut.moneytransfer.exception.TechnicalException;

public interface UserDao {
	public List<User> getAllUser();

	public User getUserByPhoneNumber(String phoneNumber);

	public int insertUser(User user);

	public int deleteUser(String phoneNumber);

	public int updateUser(User user);

}
