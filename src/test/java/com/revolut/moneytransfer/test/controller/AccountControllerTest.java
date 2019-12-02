package com.revolut.moneytransfer.test.controller;

import static junit.framework.TestCase.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.revolut.moneytransfer.entity.Account;
import com.revolut.moneytransfer.exception.TechnicalException;
import com.revolut.moneytransfer.test.ServiceTest;

public class AccountControllerTest {

	private static Logger logger = LoggerFactory.getLogger(AccountControllerTest.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testGetAllAccounts()
			throws TechnicalException, URISyntaxException, ClientProtocolException, IOException {
		URI uri = ServiceTest.createUriBuilder().setPath("/account/allAccounts").build();
		HttpGet request = new HttpGet(uri);
		request.setHeader("Content-type", "application/json");
		HttpResponse response = ServiceTest.getHttpClient().execute(request);
		int statu = response.getStatusLine().getStatusCode();
		assertTrue(statu == 200);

	}

	@Test
	public void testAccountsByAccountNumber()
			throws TechnicalException, URISyntaxException, ClientProtocolException, IOException {
		URI uri = ServiceTest.createUriBuilder().setPath("/account/accountsByAccountNumber/252502102").build();
		HttpGet request = new HttpGet(uri);
		request.setHeader("Content-type", "application/json");
		HttpResponse response = ServiceTest.getHttpClient().execute(request);
		int statu = response.getStatusLine().getStatusCode();
		assertTrue(statu == 200);

		String jsonString = EntityUtils.toString(response.getEntity());
		Account account = mapper.readValue(jsonString, Account.class);
		assertTrue(account.getPhoneNumber().equals("05439004050"));
	}

	@Test
	public void testAccountsByPhoneNumber()
			throws TechnicalException, URISyntaxException, ClientProtocolException, IOException {
		URI uri = ServiceTest.createUriBuilder().setPath("/account/accountsByPhoneNumber/05235334654").build();
		HttpGet request = new HttpGet(uri);
		request.setHeader("Content-type", "application/json");
		HttpResponse response = ServiceTest.getHttpClient().execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		assertTrue(statusCode == 200);
	}

	@Test
	public void testInsertAccount() throws URISyntaxException, ClientProtocolException, IOException {
		URI uri = ServiceTest.createUriBuilder().setPath("/account/createAccount").build();
		Account account = new Account(646567675, "05439004050", new BigDecimal(103.343), "JPY");

		String jsonInString = mapper.writeValueAsString(account);
		StringEntity entity = new StringEntity(jsonInString);
		HttpPost request = new HttpPost(uri);
		request.setHeader("Content-type", "application/json");
		request.setEntity(entity);
		HttpResponse response = ServiceTest.getHttpClient().execute(request);
		int statu = response.getStatusLine().getStatusCode();
		assertTrue(statu == 200);

	}

	@Test
	public void testUpdateAccount() throws URISyntaxException, ClientProtocolException, IOException {
		URI uri = ServiceTest.createUriBuilder().setPath("/account/updateAccount").build();
		Account account = new Account(646567865, "05429003212", new BigDecimal(400.343), "USD");

		String jsonInString = mapper.writeValueAsString(account);
		StringEntity entity = new StringEntity(jsonInString);
		HttpPut request = new HttpPut(uri);
		request.setHeader("Content-type", "application/json");
		request.setEntity(entity);
		HttpResponse response = ServiceTest.getHttpClient().execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		assertTrue(statusCode == 200);
	}

	@Test
	public void testDeleteAccount() throws URISyntaxException, ClientProtocolException, IOException {
		URI uri = ServiceTest.createUriBuilder().setPath("/account/deleteAccount/432345765").build();

		HttpDelete request = new HttpDelete(uri);
		request.setHeader("Content-type", "application/json");
		HttpResponse response = ServiceTest.getHttpClient().execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		assertTrue(statusCode == 200);
	}

}
