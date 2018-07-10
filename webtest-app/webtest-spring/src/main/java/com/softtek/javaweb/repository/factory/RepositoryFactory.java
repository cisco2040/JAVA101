package com.softtek.javaweb.repository.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.softtek.javaweb.repository.MyRepository;
import com.softtek.javaweb.repository.impl.*;
import com.softtek.javaweb.repository.types.RepoType;

@Repository
public class RepositoryFactory {
	
	@Autowired
	CartRepository cartRepository;
	@Autowired
	CityRepository cityRepository;
	@Autowired
	ShippingZoneRepository shippingZoneRepository;
	@Autowired
	ShipToRepository shipToRepositoryImpl;
	@Autowired
	StateRepository stateRepository;
	@Autowired
	StatusRepository statusRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserRoleRepository userRoleRepository;

	@SuppressWarnings("rawtypes")
	public MyRepository getRepository(RepoType repoType) {
		switch (repoType) {
		case CART:
			return cartRepository;
		case CITY:
			return cityRepository;
		case SHIPPING_ZONE:
			return shippingZoneRepository;
		case SHIP_TO:
			return (MyRepository) shipToRepositoryImpl;
		case STATE:
			return stateRepository;
		case STATUS:
			return statusRepository;
		case USER:
			return userRepository;
		case USER_ROLE:
			return userRoleRepository;
		default:
			break;
		}
		return null;		
	}
}
