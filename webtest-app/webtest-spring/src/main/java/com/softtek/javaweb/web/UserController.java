package com.softtek.javaweb.web;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.softtek.javaweb.domain.model.User;
import com.softtek.javaweb.exception.UserAlreadyExistsException;
import com.softtek.javaweb.service.UserRoleService;
import com.softtek.javaweb.service.UserService;

	@RestController
	@RequestMapping(value = "/users")
	public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	UserRoleService userRoleService;
	@Autowired
	EntityManager em;

	@GetMapping (value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<User> getUserList() {
		return userService.getList();
	}

	@GetMapping (value = "/{username}/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public User getUserById(@PathVariable ("username") String username) {
		return userService.getOne(username);
	}
	
	@PostMapping (value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public User addUser(@RequestBody User requestBody) throws UserAlreadyExistsException {		
		return userService.add(requestBody);
	}
	
	@PutMapping (value = "/{username}/", consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public User udpateUser(@PathVariable ("username") String username, @RequestBody User requestBody) {		
		return userService.update(requestBody);
	}
	
	
	@PatchMapping (value = "/{username}/", consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public User patchUser(@PathVariable ("username") String username, @RequestBody User requestBody) {		
		return userService.patchUser(username, requestBody);
	}
	
	@DeleteMapping ("/{username}/")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser (@PathVariable ("username") String username) {
		userService.delete(username);
	}
	
	
}
