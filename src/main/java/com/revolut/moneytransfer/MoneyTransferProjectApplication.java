package com.revolut.moneytransfer;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.moneytransfer.batch.SendMoneyBatch;
import com.revolut.moneytransfer.controller.AccountController;
import com.revolut.moneytransfer.controller.TransactionController;
import com.revolut.moneytransfer.controller.UserController;
import com.revolut.moneytransfer.exception.ServiceExceptionMapper;
import com.revolut.moneytransfer.factory.ConnectionFactory;

public class MoneyTransferProjectApplication {
	private static Logger logger = LoggerFactory.getLogger(MoneyTransferProjectApplication.class);

	public static void main(String[] args) throws Exception {
		logger.info("Create db starting");
		ConnectionFactory.createTestData();
		logger.info("Create db end");
		logger.info("Scheduler starting");
		startScheduler();
		logger.info("Server starting");
		startServer();
	}

	public static void startServer() throws Exception {
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
			jettyServer.join();
		} finally {
			jettyServer.destroy();
		}
	}

	public static void startScheduler() {
		SchedulerFactory shedFact = new StdSchedulerFactory();
		try {
			Scheduler scheduler = shedFact.getScheduler();
			scheduler.start();
			JobDetail job = JobBuilder.newJob(SendMoneyBatch.class).withIdentity("sendMoneyBatch", "group01").build();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("sendMoneyTrigger", "group01")
					.withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ? *")).build();
//			0 0/5 0 ? * * * = 5minute
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
