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

import com.revolut.moneytransfer.entity.User;
import com.revolut.moneytransfer.factory.ServiceFactory;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@GET
	@Path("/allUser")
	public List<User> getAllUser() {
		return ServiceFactory.getUserService().getAllUser();
	}

	@GET
	@Path("/userByPhoneNumber/{phoneNumber}")
	public User getUserByPhoneNumber(@PathParam("phoneNumber") String phoneNumber) {
		return ServiceFactory.getUserService().getUserByPhoneNumber(phoneNumber);
	}

	@POST
	@Path("/createUser")
	public Response insertUser(User user) {
		int isCreated = ServiceFactory.getUserService().insertUser(user);
		if (isCreated == 1) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("User didnt insert").build();
		}
	}

	@PUT
	@Path("/updateUser")
	public Response updateUser(User user) {
		int isUpdated = ServiceFactory.getUserService().updateUser(user);
		if (isUpdated == 1) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("User didnt update").build();
		}
	}

	@DELETE
	@Path("/deleteUser/{phoneNumber}")
	public Response deleteUser(@PathParam("phoneNumber") String phoneNumber) {
		int isDeleted = ServiceFactory.getUserService().deleteUser(phoneNumber);
		if (isDeleted == 1) {
			return Response.status(Response.Status.OK).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("User didnt delete").build();
		}
	}
}
