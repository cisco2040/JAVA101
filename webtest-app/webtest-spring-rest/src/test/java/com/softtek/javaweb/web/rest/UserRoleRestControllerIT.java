package com.softtek.javaweb.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jayway.jsonpath.JsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/test/resources/META-INF/applicationContext.xml",
		"file:src/main/webapp/WEB-INF/web-applicationContext.xml"
	})
@WebAppConfiguration( value = "classpath:WEB-INF")
public class UserRoleRestControllerIT {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void givenWac_whenServletContext_thenItProvidesGreetController() {
	    ServletContext servletContext = wac.getServletContext();
	     
	    Assert.assertNotNull(servletContext);
	    Assert.assertTrue(servletContext instanceof MockServletContext);
	    
	    System.out.println("### Mock MVC: " + mockMvc.toString());
	    System.out.println("### Bean names: " + Arrays.asList(wac.getBeanDefinitionNames()));
	    System.out.println("### Bean count: " + wac.getBeanDefinitionCount());
	    System.out.println("### Context name: " + wac.getDisplayName());
	}
	
	@Test
	public void getOne_UserRoleReturned() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/userRoles/ADM"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		
		String userRoleId = JsonPath.read(mvcResult.getResponse().getContentAsString(),"$.userRoleId");
		Assert.assertTrue(userRoleId.equals("ADM"));

	}
	
}
