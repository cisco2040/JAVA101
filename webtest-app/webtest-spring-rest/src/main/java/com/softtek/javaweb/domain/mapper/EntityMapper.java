package com.softtek.javaweb.domain.mapper;

import com.softtek.javaweb.domain.dto.CartForm;
import com.softtek.javaweb.domain.dto.ShipToForm;
import com.softtek.javaweb.domain.dto.UserForm;
import com.softtek.javaweb.domain.model.Cart;
import com.softtek.javaweb.domain.model.City;
import com.softtek.javaweb.domain.model.ShipTo;
import com.softtek.javaweb.domain.model.Status;
import com.softtek.javaweb.domain.model.User;
import com.softtek.javaweb.domain.model.UserRole;

public class EntityMapper {
	private EntityMapper() {}
	public static Cart makeCartFromForm(final CartForm cartForm) {
		Cart cart = new Cart();
		
		cart.setCartId(cartForm.getCartId());
		cart.setCartAmount(cartForm.getCartAmount());
		cart.setCreateUser(cartForm.getCreateUser());
		cart.setCreateDate(cartForm.getCreateDate());
		cart.setLinesAmount(cartForm.getLinesAmount());
		cart.setShippingAmount(cartForm.getShippingAmount());
		cart.setShipTo(new ShipTo(cartForm.getShipToId(), null, null, null, null, null, null, null ));
		cart.setStatus(new Status(cartForm.getStatusId(), null, null));
		cart.setUpdateUser(cartForm.getUpdateUser());
		
		return cart;
	}
	public static CartForm makeFormFromCart(final Cart cart) {
		CartForm cartForm = new CartForm();
		
		cartForm.setCartId(cart.getCartId());
		cartForm.setCartAmount(cart.getCartAmount());
		cartForm.setCreateUser(cart.getCreateUser());
		cartForm.setCreateDate(cart.getCreateDate());
		cartForm.setLinesAmount(cart.getLinesAmount());
		cartForm.setShippingAmount(cart.getShippingAmount());
		cartForm.setShipToId(cart.getShipTo().getShipToId());
		cartForm.setStatusId(cart.getStatus().getStatusId());
		cartForm.setUpdateUser(cart.getUpdateUser());
		
		return cartForm;
	}
	public static ShipTo makeShipToFromForm(final ShipToForm shipToForm) {
		ShipTo shipTo = new ShipTo();
		
		shipTo.setShipToId(shipToForm.getShipToId());
		shipTo.setName(shipToForm.getName());
		shipTo.setUser(new User(shipToForm.getUsername(), null, null, null,null));
		shipTo.setAddress(shipToForm.getAddress());
		shipTo.setCity(new City(shipToForm.getCityId(), null, null));
		shipTo.setEmail(shipToForm.getEmail());
		shipTo.setPhone(shipToForm.getPhone());
		shipTo.setZipcode(shipToForm.getZipcode());
		
		return shipTo;
	}
	public static ShipToForm makeFormFromShipTo(final ShipTo shipTo) {

		ShipToForm shipToForm = new ShipToForm();
		
		shipToForm.setShipToId(shipTo.getShipToId());
		shipToForm.setName(shipTo.getName());
		shipToForm.setUsername(shipTo.getUser().getUsername());
		shipToForm.setAddress(shipTo.getAddress());
		shipToForm.setCityId(shipTo.getCity().getCityId());
		shipToForm.setEmail(shipTo.getEmail());
		shipToForm.setPhone(shipTo.getPhone());
		shipToForm.setZipcode(shipTo.getZipcode());
		
		return shipToForm;
	}
	public static User makeUserFromForm(final UserForm userForm) {
		User user = new User();
		
		user.setUsername(userForm.getUsername());
		user.setPassword(userForm.getPassword());
		user.setName(userForm.getName());
		user.setActive(userForm.getActive());
		user.setUserRole(new UserRole(userForm.getUserRoleId(), null));
		
		return user;
	}
	public static UserForm makeFormFromUser(final User user) {
		UserForm userForm = new UserForm();
		
		userForm.setUsername(user.getUsername());
		userForm.setPassword(user.getPassword());
		userForm.setPasswordConfirm(user.getPassword());
		userForm.setName(user.getName());
		userForm.setActive(user.getActive());
		userForm.setUserRoleId(user.getUserRole().getUserRoleId());
		
		return userForm;
	}
}
