package com.softtek.javaweb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.dto.ResponseStatus;
import com.softtek.javaweb.domain.model.ShipTo;
import com.softtek.javaweb.repository.MyRepository;

@Service
public class ShipToService {

	@Autowired
	private MyRepository<ShipTo,Long> shipToRepository;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(ShipToService.class);
	
	public List<ShipTo> getList() {
		List<ShipTo> shipTos = this.shipToRepository.list();
		LOGGER.info("## ShipTo List Obtained: {}", shipTos);
		return shipTos;
	}

	public ShipTo getOne(final Long id) {
		ShipTo shipTo = this.shipToRepository.getOne(id);
		LOGGER.info("## ShipTo Obtained: {}", shipTo);
		return shipTo;
	}
	
	public ResponseStatus update(final ShipTo shipTo) {
		ResponseStatus validateShipTo = validate(shipTo);
		if (validateShipTo.isValid()) {
			LOGGER.info("## Attempting to update shipTo: {}", shipTo);
			if (this.shipToRepository.update(shipTo) <= 0) {
				validateShipTo.setValid(false);
				validateShipTo.appendServiceMsg("There was an unknown error while attempting to update record. Please contact DBAdmin.");				
			}
		}
		return validateShipTo;
	}
	
	public ResponseStatus add(final ShipTo shipTo) {
		ResponseStatus validateShipTo = validate(shipTo);
		if (validateShipTo.isValid()) {
			LOGGER.info("## Attempting to add shipTo: {}", shipTo);
			if (this.shipToRepository.add(shipTo) <= 0) {
				validateShipTo.setValid(false);
				validateShipTo.appendServiceMsg("There was an unknown error while attempting to add record. Please contact DBAdmin.");				
			}
		}
		return validateShipTo;
	}

	public ResponseStatus delete (final Long id) {
		ResponseStatus validateShipTo = new ResponseStatus();
		validateShipTo.setValid(true);
		if (this.shipToRepository.delete(id) <= 0) {
			validateShipTo.setValid(false);
			validateShipTo.appendServiceMsg("There was an unknown error while attempting to delete record, or record does not exist. Please contact DBAdmin.");
		}
				
		return validateShipTo;
	}
	
	public ResponseStatus validate (final ShipTo shipTo) {
		ResponseStatus validateService = new ResponseStatus();
		validateService.setValid(true);

		LOGGER.info("## Validating shipTo: {}", shipTo);

		if (shipTo.getUser().getUsername() == null) { 
			validateService.setValid(false);
			validateService.appendServiceMsg("Username is mandatory.");
		}
		if (shipTo.getName() == null) { 
			validateService.setValid(false);
			validateService.appendServiceMsg("Name is mandatory.");
		}
		if (shipTo.getAddress() == null) { 
			validateService.setValid(false);
			validateService.appendServiceMsg("Address line is mandatory.");
		}
		if (shipTo.getCity().getCityId() == null) { 
			validateService.setValid(false);
			validateService.appendServiceMsg("City is mandatory.");
		}
		if (shipTo.getZipcode() == null) { 
			validateService.setValid(false);
			validateService.appendServiceMsg("Zip Code is mandatory.");
		}
		if (shipTo.getPhone() == null) { 
			validateService.setValid(false);
			validateService.appendServiceMsg("Phone Number is mandatory.");
		}
		if (shipTo.getEmail() == null) { 
			validateService.setValid(false);
			validateService.appendServiceMsg("Email address is mandatory.");
		}
		
		LOGGER.info("## Validating shipTo status: {}", validateService.isValid());
		LOGGER.info("## Validating shipTo status messages: {}", validateService.getServiceMsg());
		
		return validateService;
	}
}