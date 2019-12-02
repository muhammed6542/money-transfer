package com.revolut.moneytransfer.controller;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.moneytransfer.entity.Account;
import com.revolut.moneytransfer.exception.TechnicalException;
import com.revolut.moneytransfer.factory.ServiceFactory;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
public class AccountController {
	private static Logger logger = LoggerFactory.getLogger(AccountController.class);

	@GET
	@Path("/allAccounts")
	public List<Account> getAllAccounts() {
		return ServiceFactory.getAccountService().getAllAccounts();
	}

	@POST
	@Path("/createAccount")
	public Response insertAccount(Account account) {

		int isCreated = ServiceFactory.getAccountService().insertAccount(account);
		if (isCreated == 1) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Account didnt insert ").build();
		}
	}

	@PUT
	@Path("/updateAccount")
	public Response updateAccount(Account account) {

		int isUpdated = ServiceFactory.getAccountService().updateAccount(account);

		if (isUpdated == 1) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Account didnt update ").build();
		}
	}

	@DELETE
	@Path("/deleteAccount/{accountNumber}")
	public Response deleteAccount(@PathParam("accountNumber") long accountNumber) {

		int isDeleted = ServiceFactory.getAccountService().deleteAccount(accountNumber);
		if (isDeleted == 1) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Account didnt delete ").build();
		}
	}

	@GET
	@Path("/accountsByAccountNumber/{accountNumber}")
	public Account getAccountsByAccountNumber(@PathParam("accountNumber") long accountNumber) {

		return ServiceFactory.getAccountService().getAccountsByAccountNumber(accountNumber);

	}

	@GET
	@Path("/accountsByPhoneNumber/{phoneNumber}")
	public List<Account> getAccountsByPhoneNumber(@PathParam("phoneNumber") String phoneNumber) {

		return ServiceFactory.getAccountService().getAccountsByPhoneNumber(phoneNumber);
	}

}
