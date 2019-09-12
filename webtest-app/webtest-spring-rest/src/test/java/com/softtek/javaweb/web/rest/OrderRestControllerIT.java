package com.softtek.javaweb.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.codehaus.jackson.map.ObjectMapper;
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
import com.softtek.javaweb.domain.model.Cart;
import com.softtek.javaweb.domain.model.Order;
import com.softtek.javaweb.domain.model.Status;
import com.softtek.javaweb.repository.jpa.OrderRepository;

import net.minidev.json.JSONArray;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/test/resources/META-INF/applicationContext.xml",
		"file:src/main/webapp/WEB-INF/web-applicationContext.xml"
	})
@WebAppConfiguration( value = "classpath:WEB-INF")
public class OrderRestControllerIT {

	@Autowired
	private WebApplicationContext wac;
	@Autowired 
	private OrderRepository orderRepository;
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();
	
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
		this.mockMvc.perform(get("/orders/1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
	
	@Test
	public void getOne_OrderNonExistent_Status204Returned() throws Exception {
        this.mockMvc
				.perform(get("/orders/999"))
				.andDo(print())
				.andExpect(status().isNoContent());
    }
	
	@Test
	public void getList_OrdersExist_OrdersReturned() throws Exception {
		MvcResult mvcResult = this.mockMvc
				.perform(get("/orders"))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
				.andReturn();
        System.out.println("### Json Response: " + mvcResult.getResponse().getContentAsString());
		
	    JSONArray jsonArray = JsonPath.read(mvcResult.getResponse().getContentAsString(),"$");
	    System.out.println("### Json Response Size: " + jsonArray.size());
	    //Assert.assertTrue(jsonArray.size() == 2);
	}
	
	@Test
	public void search_FilterOk_OrdersReturned() throws Exception {
		List<Order> orders = new ArrayList<>();
		Order order = new Order();
		order.setOrderId(1L);
		orders.add(order);
		order.setOrderId(2L);
		orders.add(order);
		
		MvcResult mvcResult = this.mockMvc
				.perform(get("/orders/search")
				.param("updateUser","jor")
				.param("paymentMethod", "CC")
				.param("createDateEnd", "2011-12-31")
				.param("createDateStart", "2014-11-01")
				.param("orderAmountMin", "10000")
				.param("statusId", "2300"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
				
	    JSONArray jsonArray = JsonPath.read(mvcResult.getResponse().getContentAsString(),"$");
	    System.out.println("### Json Response Size: " + jsonArray.size());
	    Assert.assertTrue(jsonArray.size() == 2);
	}
	
	@Test
	public void applyCoupon_CouponInvalid_ReturnException() throws Exception {
		Order order = new Order();
		order.setOrderId(1L);
		
		this.mockMvc
				.perform(patch("/orders/1")
						.param("couponId","ZZZZ")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(mapper.writeValueAsString(order)))
				.andDo(print())				
				.andExpect(status().isConflict())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}

	@Test
	public void add_OrderAdded() throws Exception {
		Order order = new Order();
		order.setOrderId(666L);
		order.setLinesAmount(99F);
		order.setCart(new Cart(32L, null, null, null, null, null, null, null, null, null));
		order.setStatus(new Status(1100L, null, null));
		
		MvcResult mvcResult = this.mockMvc
				.perform(post("/orders")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(order)))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
		
		Integer orderId = JsonPath.read(mvcResult.getResponse().getContentAsString(),"$.orderId");
		Assert.assertTrue(orderId.equals(31));
	}
	
	@Test
	public void updatePut_OrderUpdated() throws Exception {
		Order order = new Order();
		order.setOrderId(1L);
		order.setLinesAmount(99F);
		order.setCart(new Cart(29L, null, null, null, null, null, null, null, null, null));
		order.setStatus(new Status(1100L, null, null));

		MvcResult mvcResult = this.mockMvc
				.perform(put("/orders/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(order)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		Integer cartId = JsonPath.read(mvcResult.getResponse().getContentAsString(),"$.cart.cartId");
		Assert.assertTrue(cartId.equals(29));
		
	}

	@Test
	public void updatePatch_OrderUpdated() throws Exception {
		Order order = new Order();
		order.setOrderId(1L);
		order.setLinesAmount(99F);
		order.setCart(new Cart(29L, null, null, null, null, null, null, null, null, null));
		order.setStatus(new Status(1100L, null, null));

		MvcResult mvcResult = this.mockMvc
				.perform(patch("/orders/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(order)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		Integer cartId = JsonPath.read(mvcResult.getResponse().getContentAsString(),"$.cart.cartId");
		Assert.assertTrue(cartId.equals(29));
		
	}

	@Test
	public void delete_OrderDeleted() throws Exception {
		this.mockMvc
				.perform(delete("/orders/2"))
				.andDo(print())
				.andExpect(status().isNoContent());
		Assert.assertFalse(orderRepository.findById(2L).isPresent());
	}
	
}
