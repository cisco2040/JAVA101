package com.softtek.javaweb.service;

import java.util.List;

import com.softtek.javaweb.domain.model.ShippingZone;
import com.softtek.javaweb.repository.ShippingZoneRepository;

public class ShippingZoneService {
	
	private ShippingZoneRepository shippingZoneRepository = new ShippingZoneRepository();
	
	public List<ShippingZone> getList() {
		return this.shippingZoneRepository.list();
	}

	public ShippingZone getOne(final String id) {
		return this.shippingZoneRepository.getOne(id);
	}
}
