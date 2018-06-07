package com.softtek.javaweb.service;

import java.util.List;

import com.softtek.javaweb.domain.model.ShipTo;
import com.softtek.javaweb.repository.ShipToRepository;

public class ShipToService {
	
	private ShipToRepository shipToRepository = new ShipToRepository();
	
	public List<ShipTo> getList() {
		return this.shipToRepository.list();
	}

	public ShipTo getOne(final Long id) {
		return this.shipToRepository.getOne(id);
	}
}
