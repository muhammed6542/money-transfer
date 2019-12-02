package com.revolut.moneytransfer.batch;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.moneytransfer.entity.Transaction;
import com.revolut.moneytransfer.factory.ServiceFactory;

public class SendMoneyBatch implements Job {
	private static Logger logger = LoggerFactory.getLogger(SendMoneyBatch.class);

	@Override
	public synchronized void execute(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("SendMoneyBatch start");
		List<Transaction> transactionList = ServiceFactory.getTransactionService().getNotCompletedTransaction();
		if (transactionList.size() > 0) {
			for (Transaction transaction : transactionList) {
				ServiceFactory.getSendMoneyService().sendMoney(transaction);
			}
		}
	}

}
