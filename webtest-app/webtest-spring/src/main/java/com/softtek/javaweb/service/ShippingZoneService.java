package com.softtek.javaweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.javaweb.domain.model.ShippingZone;
import com.softtek.javaweb.repository.ShippingZoneRepository;

@Service
public class ShippingZoneService {
	@Autowired
	private ShippingZoneRepository shippingZoneRepository;
	
	public List<ShippingZone> getList() {
		return this.shippingZoneRepository.list();
	}

	public ShippingZone getOne(final String id) {
		return this.shippingZoneRepository.getOne(id);
	}
}
