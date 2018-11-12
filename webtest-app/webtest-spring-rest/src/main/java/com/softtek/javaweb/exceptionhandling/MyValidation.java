package com.softtek.javaweb.exceptionhandling;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.validation.BindingResult;

import com.softtek.javaweb.domain.dto.RestError;

public class MyValidation {
	private MyValidation () {}
	
	public static List<RestError> getErrorsFromBindingResults (BindingResult result) {
		List <RestError> errorMsgs = new ArrayList<>();
		result.getFieldErrors().forEach(fe -> errorMsgs.add(new RestError(fe.getField(), fe.getRejectedValue().toString(), fe.getDefaultMessage())));
		return errorMsgs;		
	}
	
	public static Validator getBeanValidator () {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		return factory.getValidator();	
	}
	
	public static <T> List<RestError> validateBean (T bean) {
		List<RestError> validationMsgs = new ArrayList<>();
		if ( bean != null ) {
			Set<ConstraintViolation<T>> violations = MyValidation.getBeanValidator().validate(bean);

			if (!violations.isEmpty()) {
				violations.forEach(v -> validationMsgs.add(new RestError(v.getPropertyPath().toString(), v.getInvalidValue() != null ? v.getInvalidValue().toString() : null, v.getMessage())));
			}
		}
		return validationMsgs;

	}
}
