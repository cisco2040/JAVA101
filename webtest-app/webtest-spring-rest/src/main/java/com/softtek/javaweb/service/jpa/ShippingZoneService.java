package com.softtek.javaweb.service.jpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.model.ShippingZone;
import com.softtek.javaweb.exception.impl.ResourceNotAvailableException;
import com.softtek.javaweb.repository.jpa.ShippingZoneRepository;

@Service
public class ShippingZoneService {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

	@Autowired
	private ShippingZoneRepository shippingZoneRepository;
	
	public List<ShippingZone> getList() throws ResourceNotAvailableException {
		List <ShippingZone> shippingZones = this.shippingZoneRepository.findAll(); 
		LOGGER.info("## Shipping Zone List Obtained: {}", shippingZones);
		if (shippingZones.isEmpty()) {
			throw new ResourceNotAvailableException("No shipping zones were found.");
		}
		return shippingZones;
	}

	public ShippingZone getOne(final String id) throws ResourceNotAvailableException {
		ShippingZone shippingZone = this.shippingZoneRepository.findById(id).orElse(null);
		LOGGER.info("## Shipping Zone Obtained: {}", shippingZone);
		if (shippingZone == null) {
			throw new ResourceNotAvailableException("Shipping zone id <" + id + "> not found.");
		}
		return shippingZone;
	}
}
