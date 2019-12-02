package com.revolut.moneytransfer.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.moneytransfer.entity.Transaction;
import com.revolut.moneytransfer.factory.ServiceFactory;

@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
public class TransactionController {
	private static Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@POST
	@Path("/sendMoney")
	public Response sendMoney(Transaction transaction) {
		int isSent = ServiceFactory.getTransactionService().insertTransaction(transaction);
		if (isSent == 1) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Transaction didnt insert").build();
		}
	}

	@GET
	@Path("/transactionBySenderAccount/{senderAccount}")
	public List<Transaction> getTransactionBySenderAccount(@PathParam("senderAccount") long senderAccount) {
		return ServiceFactory.getTransactionService().getTransactionBySenderAccount(senderAccount);
	}

	@GET
	@Path("/allTransaction")
	public List<Transaction> getAllTransaction() {
		return ServiceFactory.getTransactionService().getAllTransaction();
	}

	@GET
	@Path("/transactionByReceiverAccount/{receiverAccount}")
	public List<Transaction> getTransactionByReceiverAccount(@PathParam("receiverAccount") long receiverAccount) {
		return ServiceFactory.getTransactionService().getTransactionByReceiverAccount(receiverAccount);
	}

	@GET
	@Path("/notCompletedTransaction")
	public List<Transaction> getNotCompletedTransaction() {
		return ServiceFactory.getTransactionService().getNotCompletedTransaction();
	}

	@POST
	@Path("/acceptMoney")
	public Response acceptMoney(Transaction transaction) {
		return ServiceFactory.getSendMoneyService().acceptMoney(transaction);

	}

}
