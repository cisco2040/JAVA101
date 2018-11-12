package com.softtek.javaweb.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletContext;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/applicationContext.xml","file:src/main/webapp/WEB-INF/web-applicationContext.xml"})
@WebAppConfiguration( value = "classpath:WEB-INF")
public class OrderRestControllerTest {

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
	public void getOne_OrderExists_OrderReturned() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/orders/1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.orderId").value(1L))
				.andReturn();
		Assert.assertEquals("application/json;charset=UTF-8",
				mvcResult.getResponse().getContentType());
	}
	
	@Test
	public void getOne_OrderNonExistent_Status204Returned() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/orders/999"))
				.andDo(print())
				.andExpect(status().isNoContent())
				.andReturn();
		Assert.assertEquals(204, mvcResult.getResponse().getStatus());
	}
	
	@Test
	public void getList_OrdersExist_OrdersReturned() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/orders"))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();
	    System.out.println("### Json Response: " + mvcResult.getResponse().getContentAsString());
		
	    JSONArray jsonArray = JsonPath.read(mvcResult.getResponse().getContentAsString(),"$");
//	    Map<String, String> jsonMap = JsonPath.read(mvcResult.getResponse().getContentAsString(),"$"); 

	    System.out.println("### Json Response Size: " + jsonArray.size());
	    Assert.assertTrue(jsonArray.size() > 1);
	}
}
