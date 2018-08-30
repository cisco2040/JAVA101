package com.softtek.javaweb.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.softtek.javaweb.domain.dto.RestResponse;
import com.softtek.javaweb.exception.JavawebException;
import com.softtek.javaweb.exception.impl.*;
import com.softtek.javaweb.exceptionhandling.types.RestStatusCodes;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
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

	private RestResponse setResponse (RestStatusCodes code, JavawebException e) {
		RestResponse response = new RestResponse();

		response.setStatusCode(code.getStatusCode());
		response.setMessage(code.getStatusMessage());
		response.setDescription(e.getMessage());
		if (e.getViolations() != null) {
			response.setErrors(e.getViolations());
		}
		
		return response;		
	}
	
}
