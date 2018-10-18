package com.softtek.javaweb.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.softtek.javaweb.domain.model.UserRole;
import com.softtek.javaweb.exception.impl.OperationNotSupportedException;
import com.softtek.javaweb.exception.impl.ResourceNotAvailableException;
import com.softtek.javaweb.service.jpa.UserRoleService;

@RestController
@RequestMapping("/userRoles")
public class UserRoleRestController {
	
	@Autowired
	UserRoleService userRoleService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public UserRole getOne(@PathVariable String id) throws ResourceNotAvailableException {
		return userRoleService.getOne(id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<UserRole> getList() throws ResourceNotAvailableException {
		return userRoleService.getList();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	@ResponseStatus(HttpStatus.CREATED)
	public UserRole add(@RequestBody UserRole userRole) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("POST operation not supported for User Role resource");
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void updatePut(@RequestBody UserRole userRole, @PathVariable String id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("PUT operation not supported for User Role resource");
	}
	
	@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public void updatePatch(@RequestBody UserRole userRole, @PathVariable String id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("PATCH operation not supported for User Role resource");
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("DELETE operation not supported for User Role resource");
	}
}