package com.softtek.javaweb.domain.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.softtek.javaweb.domain.model.User;

public class MyUserPrincipal implements UserDetails {

	private static final long serialVersionUID = 7644463598006103128L;
	
	private User user;	

	public MyUserPrincipal(User user) {
		this.user = user;
	}

	@Override
	public Collection<SimpleGrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>(); 
		authorities.add(new SimpleGrantedAuthority(user.getUserRole().getUserRoleId()));
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.getActive().equals("S") ? true : false;
	}
}
