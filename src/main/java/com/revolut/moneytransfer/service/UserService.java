package com.revolut.moneytransfer.service;

import java.util.List;

import com.revolut.moneytransfer.entity.User;
import com.revolut.moneytransfer.exception.TechnicalException;

public interface UserService {

	public List<User> getAllUser() throws TechnicalException;

	public User getUserByPhoneNumber(String phoneNumber) throws TechnicalException;

	public int insertUser(User user) throws TechnicalException;

	public int updateUser(User user) throws TechnicalException;

	public int deleteUser(String phoneNumber) throws TechnicalException;
}
