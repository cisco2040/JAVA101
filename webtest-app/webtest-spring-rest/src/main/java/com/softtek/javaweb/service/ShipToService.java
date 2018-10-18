package com.softtek.javaweb.service;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.dto.RestError;
import com.softtek.javaweb.domain.model.City;
import com.softtek.javaweb.domain.model.ShipTo;
import com.softtek.javaweb.domain.model.User;
import com.softtek.javaweb.exception.impl.ResourceNotAddedException;
import com.softtek.javaweb.exception.impl.ResourceNotAvailableException;
import com.softtek.javaweb.exception.impl.ResourceNotDeletedException;
import com.softtek.javaweb.exception.impl.ResourceCouldNotBeFoundException;
import com.softtek.javaweb.exception.impl.ResourceNotUpdatedException;
import com.softtek.javaweb.exceptionhandling.MyValidation;
import com.softtek.javaweb.repository.MyRepository;

@Service
public class ShipToService {

	@Autowired
	private MyRepository<ShipTo,Long> shipToRepository;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(ShipToService.class);
	
	public List<ShipTo> getList() throws ResourceNotAvailableException {
		List<ShipTo> shipTos = this.shipToRepository.list();
		LOGGER.info("## ShipTo List Obtained: {}", shipTos);
		if (shipTos.isEmpty()) {
			throw new ResourceNotAvailableException("No Ship-To addresses were found.");
		}
		return shipTos;
	}

	public ShipTo getOne(final Long id) throws ResourceNotAvailableException {
		ShipTo shipTo = this.shipToRepository.getOne(id);
		LOGGER.info("## ShipTo Obtained: {}", shipTo);
		if (shipTo == null) {
			throw new ResourceNotAvailableException("Ship-To id <" + id + "> not found.");
		}
		return shipTo;
	}

	public ShipTo updateFull(final ShipTo shipTo, final Long id) throws ResourceNotUpdatedException, ResourceCouldNotBeFoundException {
		ShipTo fullShipTo = shipTo;
		fullShipTo.setShipToId(id);
		return this.update(fullShipTo);
	}

	public ShipTo updatePartial(final ShipTo shipTo, final Long id) throws ResourceCouldNotBeFoundException, ResourceNotUpdatedException {
		ShipTo fullShipTo = this.shipToRepository.getOne(id);
		
		if (fullShipTo == null) {
			throw new ResourceCouldNotBeFoundException("Ship-To id <" + id + "> not found.");
		}
		
		ShipTo newShipTo = mergeBeans(shipTo, fullShipTo);
		List<RestError> restErrors = MyValidation.validateBean(newShipTo);

		if (restErrors.isEmpty()) {
			LOGGER.info("## Attempting to update (PATCH) shipTo: {}, with elements: {}", id, shipTo);
			if (this.shipToRepository.update(newShipTo) == null) {
				throw new ResourceNotUpdatedException("Could not update Ship-To address due to unknown problems during persist.");
			}
		} else {
			throw new ResourceNotUpdatedException("Could not update Ship-To address due to missing/incorrect data.", restErrors);
		}
		
		return shipToRepository.getOne(id);
	}

	public ShipTo update(final ShipTo shipTo) throws ResourceNotUpdatedException, ResourceCouldNotBeFoundException {
		List<RestError> restErrors = MyValidation.validateBean(shipTo);
		
		if (!isUnique(shipTo)) {
			if (restErrors.isEmpty()) {
				LOGGER.info("## Attempting to update Ship-To address: {}", shipTo);
				if (this.shipToRepository.update(shipTo) == null ) {
					throw new ResourceNotUpdatedException("Could not update Ship-To address due to unknown problems during persist.");
				}
			} else {
				throw new ResourceNotUpdatedException("Could not update Ship-To address due to missing/incorrect data.", restErrors);
			}
		} else {
			throw new ResourceCouldNotBeFoundException("Ship-To id <" + shipTo.getShipToId() + "> not found.");
		}

		return shipToRepository.getOne(shipTo.getShipToId());
	}
	public ShipTo add(final ShipTo shipTo) throws ResourceNotAddedException {
		List<RestError> restErrors = MyValidation.validateBean(shipTo);
		ShipTo newShipTo;
		
		if (isUnique(shipTo)) {
			if (restErrors.isEmpty()) {
				LOGGER.info("## Attempting to add shipTo: {}", shipTo);
				newShipTo = this.shipToRepository.add(shipTo); 
				if (newShipTo == null) {
					throw new ResourceNotAddedException("Could not add Ship-To address due to unknown problems during persist.");
				}
			} else {
				throw new ResourceNotAddedException("Could not add Ship-To address due to missing/incorrect data.", restErrors);			
			}
		} else {			
			throw new ResourceNotAddedException("Ship-To id <" + shipTo.getShipToId() + "> already exists. Please post a PUT or PATCH request if you mean to udpate this resource.");
		}

		return newShipTo;
	}

	public void delete (final Long id) throws ResourceNotDeletedException {
		if (this.shipToRepository.getOne(id) == null ) {
			throw new ResourceNotDeletedException("Ship-To id <" + id + "> not found.");			
		} else if (this.shipToRepository.delete(id) <= 0) {
			throw new ResourceNotDeletedException("Could not delete Ship-To address due to unknwon problems during delete process.");			
		}
	}
	
	private ShipTo mergeBeans(final ShipTo partialShipTo, final ShipTo fullShipTo) {
		ShipTo newShipTo = fullShipTo;
		
		if (partialShipTo.getAddress() != null ) {
			newShipTo.setAddress(partialShipTo.getAddress());
		}
		if (partialShipTo.getCity() != null && partialShipTo.getCity().getCityId() != null) {
			newShipTo.setCity(new City(partialShipTo.getCity().getCityId(), null, null));
		}
		if (partialShipTo.getEmail() != null ) {
			newShipTo.setEmail(partialShipTo.getEmail());
		}
		if (partialShipTo.getName() != null ) {
			newShipTo.setName(partialShipTo.getName());
		}
		if (partialShipTo.getPhone() != null ) {
			newShipTo.setName(partialShipTo.getPhone());
		}
		if (partialShipTo.getUser() != null && partialShipTo.getUser().getUsername() != null ) {
			newShipTo.setUser(new User(partialShipTo.getUser().getUsername(), null, null, null, null));
		}
		if (partialShipTo.getZipcode() != null ) {
			newShipTo.setZipcode(partialShipTo.getZipcode());
		}
		
		return newShipTo;
	}

	private boolean isUnique(final ShipTo shipTo) {
		LOGGER.info("## Validating shipTo (unique): {}", shipTo);
		return this.shipToRepository.getOne(shipTo.getShipToId()) == null ? true : false;
	}
}