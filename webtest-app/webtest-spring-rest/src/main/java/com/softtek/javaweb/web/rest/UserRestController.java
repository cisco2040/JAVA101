package com.softtek.javaweb.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.softtek.javaweb.domain.model.User;
import com.softtek.javaweb.exception.impl.ResourceNotAddedException;
import com.softtek.javaweb.exception.impl.ResourceNotAvailableException;
import com.softtek.javaweb.exception.impl.ResourceNotDeletedException;
import com.softtek.javaweb.exception.impl.ResourceCouldNotBeFoundException;
import com.softtek.javaweb.exception.impl.ResourceNotUpdatedException;
import com.softtek.javaweb.service.jpa.UserService;

@RestController
@RequestMapping("/users")
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public User getOne(@PathVariable String id) throws ResourceNotAvailableException {
		return userService.getOne(id);
	}
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<User> getList() throws ResourceNotAvailableException {
		return userService.getList();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	@ResponseStatus(HttpStatus.CREATED)
	public User add(@RequestBody User user) throws ResourceNotAddedException {
		return userService.add(user);
	}
	
	@PutMapping(value = "/{id}/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public User updatePut(@RequestBody User user, @PathVariable String id) throws ResourceNotUpdatedException, ResourceCouldNotBeFoundException {
		return userService.updateFull(user, id);
	}
	
	@PatchMapping(value = "/{id}/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public User updatePatch(@RequestBody User user, @PathVariable String id) throws ResourceNotUpdatedException, ResourceCouldNotBeFoundException {
		return userService.updatePartial(user, id);
	}

	@DeleteMapping(value = "/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public User delete(@PathVariable String id) throws ResourceNotDeletedException {
		userService.delete(id);
		return null;
	}
}