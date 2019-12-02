package com.revolut.moneytransfer.test;

import org.apache.http.client.HttpClient;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.moneytransfer.controller.AccountController;
import com.revolut.moneytransfer.controller.TransactionController;
import com.revolut.moneytransfer.controller.UserController;
import com.revolut.moneytransfer.exception.ServiceExceptionMapper;
import com.revolut.moneytransfer.factory.ConnectionFactory;

public class ServiceTest {

	protected static Server server = null;
	protected static PoolingHttpClientConnectionManager connManager;
	protected static HttpClient client;
	private static Logger logger = LoggerFactory.getLogger(ServiceTest.class);

	@BeforeClass
	public static void setup() throws Exception {
		logger.info("ServiceTest setup started");
		ConnectionFactory.createTestData();
		startServer();
		connManager = getHttpConnectionMan();
		client = getHttpClient();

	}

	@AfterClass
	public static void closeClient() throws Exception {
		// server.stop();
		HttpClientUtils.closeQuietly(client);
	}

	public static void startServer() throws Exception {
		logger.info("ServiceTest server started");
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");

		Server jettyServer = new Server(3020);
		jettyServer.setHandler(context);

		ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		jerseyServlet.setInitOrder(0);

		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
				AccountController.class.getCanonicalName() + "," + TransactionController.class.getCanonicalName() + ","
						+ UserController.class.getCanonicalName() + ","
						+ ServiceExceptionMapper.class.getCanonicalName());

		try {
			jettyServer.start();
//			jettyServer.join();
		} finally {
//			jettyServer.destroy();
		}
	}

	public static URIBuilder createUriBuilder() {
		return new URIBuilder().setScheme("http").setHost("localhost:3020");
	}

	public static HttpClient getHttpClient() {
		return HttpClients.custom().setConnectionManager(connManager).setConnectionManagerShared(true).build();
	}

	public static PoolingHttpClientConnectionManager getHttpConnectionMan() {
		connManager.setDefaultMaxPerRoute(100);
		connManager.setMaxTotal(200);
		return connManager;
	}
}
