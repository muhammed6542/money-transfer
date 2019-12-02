package com.revolut.moneytransfer.test.dao;

import static junit.framework.TestCase.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.moneytransfer.entity.User;
import com.revolut.moneytransfer.exception.TechnicalException;
import com.revolut.moneytransfer.factory.DaoFactory;

public class UserDaoTest {

	private static Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

	@Test
	public void testGetAllUsers() throws TechnicalException {
		List<User> allUser = DaoFactory.getUserDao().getAllUser();
		assertTrue(allUser.size() > 1);
	}

	@Test
	public void testUsersByPhoneNumber() throws TechnicalException {
		User user = DaoFactory.getUserDao().getUserByPhoneNumber("05439004050");
		assertTrue(user != null);
		assertTrue("05439004050".equals(user.getPhoneNumber()));

	}

	@Test
	public void testInsertUser() throws URISyntaxException, ClientProtocolException, IOException {
		User user = new User("Mehmet", "Yaman", "05467654322", "mehmet.yaman@my.com");
		DaoFactory.getUserDao().insertUser(user);
		User afterInsertUser = DaoFactory.getUserDao().getUserByPhoneNumber("05467654322");
		assertTrue("Mehmet".equals(afterInsertUser.getName()));
		assertTrue("Yaman".equals(afterInsertUser.getSurname()));
		assertTrue("05467654322".equals(afterInsertUser.getPhoneNumber()));
		assertTrue("mehmet.yaman@my.com".equals(afterInsertUser.getEmailAdress()));

	}

	@Test
	public void testUpdateUser() throws URISyntaxException, ClientProtocolException, IOException {
		// User user = new User("Mehmet", "Yaman", "05467654322",
		// "mehmet.yaman@my.com");
		User user = DaoFactory.getUserDao().getUserByPhoneNumber("05439004050");
		user.setName("Ahmet");
		user.setSurname("Cakir");
		user.setEmailAdress("ahmet.cakir@ck.com");
		DaoFactory.getUserDao().updateUser(user);
		User afterUpdateUser = DaoFactory.getUserDao().getUserByPhoneNumber("05439004050");
		assertTrue("Ahmet".equals(afterUpdateUser.getName()));
		assertTrue("Cakir".equals(afterUpdateUser.getSurname()));
		assertTrue("05439004050".equals(afterUpdateUser.getPhoneNumber()));
		assertTrue("ahmet.cakir@ck.com".equals(afterUpdateUser.getEmailAdress()));

	}

	@Test
	public void testDeleteUser() throws URISyntaxException, ClientProtocolException, IOException {
		DaoFactory.getUserDao().deleteUser("05467654322");
		User afterDeleteUser = DaoFactory.getUserDao().getUserByPhoneNumber("05467654322");
		assertTrue(afterDeleteUser == null);
	}

}
