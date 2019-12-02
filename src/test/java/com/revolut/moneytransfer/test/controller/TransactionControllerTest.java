package com.revolut.moneytransfer.test.controller;

import static junit.framework.TestCase.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revolut.moneytransfer.controller.TransactionController;
import com.revolut.moneytransfer.entity.Transaction;
import com.revolut.moneytransfer.exception.TechnicalException;
import com.revolut.moneytransfer.test.ServiceTest;

public class TransactionControllerTest {
	private static Logger logger = LoggerFactory.getLogger(TransactionController.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testSendMoney() throws URISyntaxException, ClientProtocolException, IOException {
		URI uri = ServiceTest.createUriBuilder().setPath("/transaction/sendMoney").build();
		Transaction transaction = new Transaction(252502143, 432345675, null, null, new BigDecimal(2), "TRY", "TRY",
				"W", 0, null);
		String jsonInString = mapper.writeValueAsString(transaction);
		StringEntity entity = new StringEntity(jsonInString);
		HttpPost request = new HttpPost(uri);
		request.setHeader("Content-type", "application/json");
		request.setEntity(entity);
		HttpResponse response = ServiceTest.getHttpClient().execute(request);
		int statu = response.getStatusLine().getStatusCode();
		assertTrue(statu == 200);

	}

	@Test
	public void testGetTransactionBySenderAccount()
			throws TechnicalException, URISyntaxException, ClientProtocolException, IOException {
		URI uri = ServiceTest.createUriBuilder().setPath("/transaction/transactionBySenderAccount/232216643").build();
		HttpGet request = new HttpGet(uri);
		request.setHeader("Content-type", "application/json");
		HttpResponse response = ServiceTest.getHttpClient().execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		assertTrue(statusCode == 200);

	}

	@Test
	public void testGetAllTransaction()
			throws TechnicalException, URISyntaxException, ClientProtocolException, IOException {
		URI uri = ServiceTest.createUriBuilder().setPath("/transaction/allTransaction").build();
		HttpGet request = new HttpGet(uri);
		request.setHeader("Content-type", "application/json");
		HttpResponse response = ServiceTest.getHttpClient().execute(request);
		int statu = response.getStatusLine().getStatusCode();
		assertTrue(statu == 200);

	}

	@Test
	public void testGetTransactionByReceiverAccount()
			throws TechnicalException, URISyntaxException, ClientProtocolException, IOException {
		URI uri = ServiceTest.createUriBuilder().setPath("/transaction/transactionByReceiverAccount/925490733").build();
		HttpGet request = new HttpGet(uri);
		request.setHeader("Content-type", "application/json");
		HttpResponse response = ServiceTest.getHttpClient().execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		assertTrue(statusCode == 200);
	}

	@Test
	public void testGetNotCompletedTransaction()
			throws TechnicalException, URISyntaxException, ClientProtocolException, IOException {
		URI uri = ServiceTest.createUriBuilder().setPath("/transaction/notCompletedTransaction").build();
		HttpGet request = new HttpGet(uri);
		request.setHeader("Content-type", "application/json");
		HttpResponse response = ServiceTest.getHttpClient().execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		assertTrue(statusCode == 200);
	}

	@Test
	public void testAcceptMoney() throws TechnicalException, URISyntaxException, ClientProtocolException, IOException {
		URI uri = ServiceTest.createUriBuilder().setPath("/transaction/acceptMoney").build();
		Transaction transaction = new Transaction(252502143, 432345675, null, null, new BigDecimal(234), "TRY", "TRY",
				"W", 0, null);
		String jsonInString = mapper.writeValueAsString(transaction);
		StringEntity entity = new StringEntity(jsonInString);
		HttpPost request = new HttpPost(uri);
		request.setHeader("Content-type", "application/json");
		request.setEntity(entity);
		HttpResponse response = ServiceTest.getHttpClient().execute(request);
		int statu = response.getStatusLine().getStatusCode();
		assertTrue(statu == 200);
	}

}
