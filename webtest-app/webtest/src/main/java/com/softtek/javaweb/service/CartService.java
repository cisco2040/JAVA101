package com.softtek.javaweb.service;

import java.util.List;

import com.softtek.javaweb.domain.model.Cart;
import com.softtek.javaweb.repository.CartRepository;

public class CartService {
	
	private CartRepository cartRepository = new CartRepository();
	
	public List<Cart> getList() {
		return this.cartRepository.list();
	}

	public Cart getOne(final Long id) {
		return this.cartRepository.getOne(id);
	}
}
