package com.revolut.moneytransfer.test.controller;

import static junit.framework.TestCase.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revolut.moneytransfer.entity.User;
import com.revolut.moneytransfer.exception.TechnicalException;
import com.revolut.moneytransfer.test.ServiceTest;

public class UserControllerTest {
	private static Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testGetAllUser() throws TechnicalException, URISyntaxException, ClientProtocolException, IOException {
		URI uri = ServiceTest.createUriBuilder().setPath("/user/allUser").build();
		HttpGet request = new HttpGet(uri);
		request.setHeader("Content-type", "application/json");
		HttpResponse response = ServiceTest.getHttpClient().execute(request);
		int statu = response.getStatusLine().getStatusCode();
		assertTrue(statu == 200);
	}

	@Test
	public void getUserByPhoneNumber()
			throws TechnicalException, URISyntaxException, ClientProtocolException, IOException {
		URI uri = ServiceTest.createUriBuilder().setPath("/user/userByPhoneNumber/05429003212").build();
		HttpGet request = new HttpGet(uri);
		request.setHeader("Content-type", "application/json");
		HttpResponse response = ServiceTest.getHttpClient().execute(request);
		int statu = response.getStatusLine().getStatusCode();
		assertTrue(statu == 200);

		String jsonString = EntityUtils.toString(response.getEntity());
		User user = mapper.readValue(jsonString, User.class);
		assertTrue(user.getPhoneNumber().equals("05429003212"));
	}

	@Test
	public void testInsertUser() throws URISyntaxException, ClientProtocolException, IOException {
		URI uri = ServiceTest.createUriBuilder().setPath("/user/createUser").build();
		User user = new User("sedat", "baloglu", "05467897654", "sedat.baloglu@sdb.com");

		String jsonInString = mapper.writeValueAsString(user);
		StringEntity entity = new StringEntity(jsonInString);
		HttpPost request = new HttpPost(uri);
		request.setHeader("Content-type", "application/json");
		request.setEntity(entity);
		HttpResponse response = ServiceTest.getHttpClient().execute(request);
		int statu = response.getStatusLine().getStatusCode();
		assertTrue(statu == 200);
	}

	@Test
	public void testUpdateUser() throws URISyntaxException, ClientProtocolException, IOException {
		URI uri = ServiceTest.createUriBuilder().setPath("/user/updateUser").build();
		User user = new User("sedat", "baloglu", "05394565332", "sedat.baloglu@sdb.com");

		String jsonInString = mapper.writeValueAsString(user);
		StringEntity entity = new StringEntity(jsonInString);
		HttpPut request = new HttpPut(uri);
		request.setHeader("Content-type", "application/json");
		request.setEntity(entity);
		HttpResponse response = ServiceTest.getHttpClient().execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		assertTrue(statusCode == 200);
	}

	@Test
	public void deleteUser() throws URISyntaxException, ClientProtocolException, IOException {
		URI uri = ServiceTest.createUriBuilder().setPath("/user/deleteUser/05467875421").build();

		HttpDelete request = new HttpDelete(uri);
		request.setHeader("Content-type", "application/json");
		HttpResponse response = ServiceTest.getHttpClient().execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		assertTrue(statusCode == 200);
	}

}
