package com.softtek.javaweb.exceptionhandling;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.softtek.javaweb.domain.dto.RestResponse;
import com.softtek.javaweb.exception.JavawebException;
import com.softtek.javaweb.exception.impl.*;
import com.softtek.javaweb.exceptionhandling.types.RestStatusCodes;
import org.springframework.web.HttpRequestMethodNotSupportedException;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public @ResponseBody ResponseEntity<RestResponse> handleDataIntegrityViolation (final Exception e) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>(setResponse(RestStatusCodes.C501_DATA_INTEGRITY_ERROR, new JavawebException(e.getMessage())), headers, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public @ResponseBody ResponseEntity<RestResponse> handlePageNotFound (final Exception e) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>(setResponse(RestStatusCodes.C401_NOT_ALLOWED, new JavawebException(e.getMessage() + ". Please make sure the endpoint is valid.")), headers, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public @ResponseBody ResponseEntity<RestResponse> genericNotAllowed (final Exception e) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>(setResponse(RestStatusCodes.C401_NOT_ALLOWED, new JavawebException(e.getMessage() + ". Operations allowed: GET, POST, PUT, PATCH, DELETE.")), headers, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(OperationNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public @ResponseBody RestResponse methodNotAllowed (final JavawebException e) {
		return setResponse(RestStatusCodes.C401_NOT_ALLOWED, e);
	}	
	
	@ExceptionHandler(ResourceNotAvailableException.class)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public @ResponseBody RestResponse handleResourceNotAvailable (final JavawebException e) {
		return null;
	}	
	
	@ExceptionHandler(ResourceCouldNotBeFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public @ResponseBody RestResponse handleResourceNotFound (final JavawebException e) {
		return setResponse(RestStatusCodes.C101_NOT_FOUND, e);
	}	
	
	@ExceptionHandler(ResourceNotAddedException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public @ResponseBody RestResponse handleResourceNotAdded (final JavawebException e) {
		return setResponse(RestStatusCodes.C201_NOT_ADDED, e);
	}	

	@ExceptionHandler(ResourceNotUpdatedException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public @ResponseBody RestResponse handleResourceNotUpdated (final JavawebException e) {
		return setResponse(RestStatusCodes.C202_NOT_UPDATED, e);
	}	

	@ExceptionHandler(ResourceNotDeletedException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public @ResponseBody RestResponse handleResourceNotDeleted (final JavawebException e) {
		return setResponse(RestStatusCodes.C301_NOT_DELETED, e);
	}	

	@ExceptionHandler(IncorrectParametersException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public @ResponseBody RestResponse handleIncorrectParams (final JavawebException e) {
		return setResponse(RestStatusCodes.C601_INCORRECT_PARAMS, e);
	}	

	private RestResponse setResponse (RestStatusCodes code, JavawebException e) {
		RestResponse response = new RestResponse();

		response.setStatusCode(code.getStatusCode());
		response.setMessage(code.getStatusMessage());
		response.setDescription(e.getMessage());
		if (e.getRestErrors() != null) {
			response.setFieldErrors(e.getRestErrors());
		}
		
		return response;		
	}
	
}
