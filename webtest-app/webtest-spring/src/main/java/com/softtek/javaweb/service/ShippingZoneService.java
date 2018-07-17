package com.softtek.javaweb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.model.ShippingZone;
import com.softtek.javaweb.repository.MyRepository;

@Service
public class ShippingZoneService {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(CartService.class);

	@Autowired
	private MyRepository<ShippingZone,String> shippingZoneRepository;
	
	public List<ShippingZone> getList() {
		List <ShippingZone> shippingZones = this.shippingZoneRepository.list(); 
		LOGGER.info("## Shipping Zone List Obtained: {}", shippingZones);
		return shippingZones;
	}

	public ShippingZone getOne(final String id) {
		ShippingZone shippingZone = this.shippingZoneRepository.getOne(id);
		LOGGER.info("## Shipping Zone Obtained: {}", shippingZone);
		return shippingZone;
	}
}
