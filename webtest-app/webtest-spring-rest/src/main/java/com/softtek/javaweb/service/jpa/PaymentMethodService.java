package com.softtek.javaweb.service.jpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.model.PaymentMethod;
import com.softtek.javaweb.exception.impl.ResourceNotAvailableException;
import com.softtek.javaweb.repository.jpa.PaymentMethodRepository;

@Service
public class PaymentMethodService {

	public static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

	@Autowired
	private PaymentMethodRepository paymentMethodRepository;
	
	public List<PaymentMethod> getList() throws ResourceNotAvailableException {
		List<PaymentMethod> paymentMethods = this.paymentMethodRepository.findAll();
		LOGGER.info("## Payment Method List Obtained: {}", paymentMethods);
		if (paymentMethods.isEmpty()) {
			throw new ResourceNotAvailableException("No payment methods were found.");
		}
		return paymentMethods; 
	}

	public PaymentMethod getOne(final String id) throws ResourceNotAvailableException {
		PaymentMethod paymentMethod = this.paymentMethodRepository.findById(id).orElse(null);
		LOGGER.info("## Payment Method Obtained: {}", paymentMethod);
		if (paymentMethod == null) {
			throw new ResourceNotAvailableException("Payment Method id <" + id + "> not found.");
		}
		return paymentMethod;
	}
}
