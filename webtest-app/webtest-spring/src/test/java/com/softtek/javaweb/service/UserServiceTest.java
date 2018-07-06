package com.softtek.javaweb.service;

import static org.junit.Assert.*;

import org.apache.commons.lang3.StringUtils;

import com.softtek.javaweb.domain.dto.ResponseStatus;
import com.softtek.javaweb.domain.model.User;
import com.softtek.javaweb.domain.model.UserRole;
import com.softtek.javaweb.service.UserService;

import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.FixMethodOrder;
import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {
	UserService userService = new UserService();
	ResponseStatus validateService = new ResponseStatus();
	String testUsername = "test.user"; 
	String confirmPassword = "mypassword"; // should match user.setPassword property
	User user = new User(); // create Object
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/META-INF/jdbc-applicationContext.xml");

	@Test
	public void testAshouldCreateUser() {
		// setup dummy values

		user.setUsername(testUsername);
		user.setName("Test User");
		user.setPassword(confirmPassword);
		user.setUserRole(new UserRole("USR",StringUtils.EMPTY)); // USR must exist in mydb.user table
		user.setActive("S");
		
		validateService = userService.add(user, confirmPassword);
		
		assertTrue(validateService.isValid());		
	}

	@Test
	public void testBshouldUpdateUser() {
		String nameTest = "Test User updated";

		user.setUsername(testUsername);
		user.setName(nameTest);
		user.setPassword(confirmPassword);
		user.setUserRole(new UserRole("USR",StringUtils.EMPTY)); // USR must exist in mydb.user table
		user.setActive("S");
		
		validateService = userService.update(user, confirmPassword);
		
		assertTrue(validateService.isValid());		
		assertTrue(user.getName().equals(nameTest));		
	}

	@Test
	public void testCshouldDeleteUser() {
	
		validateService = userService.delete(testUsername);
		
		assertTrue(validateService.isValid());		
	}
}
