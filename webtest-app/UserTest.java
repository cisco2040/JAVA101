package webtest;

import static org.junit.Assert.*;

import org.apache.commons.lang3.StringUtils;

import com.softtek.javaweb.domain.model.User;
import com.softtek.javaweb.domain.model.UserRole;
import com.softtek.javaweb.service.UserService;
import com.softtek.javaweb.service.ValidateService;

import org.junit.Test;

public class UserTest {
	UserService userService = new UserService();
	ValidateService validateService = new ValidateService();
	User user = new User(); // create Object
	String testUsername = "test.user"; // should match user.setPassword property
	String confirmPassword = "mypassword"; // should match user.setPassword property
	
	@Test
	public void shouldCreateUser() {
		// setup dummy values
		user.setUsername(testUsername);
		user.setName("Test User");
		user.setPassword("mypassword");
		user.setUserRole(new UserRole("USR",StringUtils.EMPTY)); // USR must exist in mydb.user table
		user.setActive("S");
		
		validateService = userService.add(user, confirmPassword);
		
		assertTrue(validateService.isValid());		
	}

	@Test
	public void shouldUpdateUser() {
		String nameTest = "Test User updated";

		user.setName(nameTest);
		
		validateService = userService.update(user, confirmPassword);
		
		assertTrue(validateService.isValid());		
		assertTrue(user.getName().equals(nameTest));		
	}

	@Test
	public void shouldDeleteUser() {
	
		validateService = userService.delete(testUsername);
		
		assertTrue(validateService.isValid());		
	}

}
