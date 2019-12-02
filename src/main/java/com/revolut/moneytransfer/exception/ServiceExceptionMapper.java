package com.revolut.moneytransfer.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<TechnicalException> {
	private static Logger logger = LoggerFactory.getLogger(ServiceExceptionMapper.class);

	public ServiceExceptionMapper() {
	}

	public Response toResponse(TechnicalException daoException) {
		logger.error(daoException.getMessage(), daoException);
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(daoException.getMessage())
				.type(MediaType.APPLICATION_JSON).build();
	}

}
