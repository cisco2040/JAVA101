package com.softtek.javaweb.web.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.util.NestedServletException;

import com.jayway.jsonpath.JsonPath;
import com.softtek.javaweb.domain.model.Order;
import com.softtek.javaweb.exception.impl.ResourceNotAvailableException;
import com.softtek.javaweb.exception.impl.ResourceNotUpdatedException;
import com.softtek.javaweb.service.filter.OrderFilter;
import com.softtek.javaweb.service.jpa.OrderService;

import net.minidev.json.JSONArray;

@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:META-INF/applicationContext.xml","file:src/main/webapp/WEB-INF/web-applicationContext.xml"})
//@WebAppConfiguration( value = "classpath:WEB-INF")
public class OrderRestControllerTest {

//	@Autowired
//	private WebApplicationContext wac;
//
//	private MockMvc mockMvc;
	
	@InjectMocks
	private OrderRestController orderRestController;
	@Mock
	private OrderService orderService;

//	@Before
//	public void setup() throws Exception {
//	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//	}
	
//	@Test
//	public void givenWac_whenServletContext_thenItProvidesGreetController() {
//	    ServletContext servletContext = wac.getServletContext();
//	     
//	    Assert.assertNotNull(servletContext);
//	    Assert.assertTrue(servletContext instanceof MockServletContext);
//	    
//	    System.out.println("### Mock MVC: " + mockMvc.toString());
//	    System.out.println("### Bean names: " + Arrays.asList(wac.getBeanDefinitionNames()));
//	    System.out.println("### Bean count: " + wac.getBeanDefinitionCount());
//	    System.out.println("### Context name: " + wac.getDisplayName());
//	}
	
	@Test
	public void getOne_OrderExists_OrderReturned() throws Exception {
		when(orderService.getOne(1L)).thenReturn(new Order());

//		MvcResult mvcResult = this.mockMvc.perform(get("/orders/1"))
        MockMvcBuilders.standaloneSetup(orderRestController).build()
        		.perform(get("/orders/1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8));
//		Assert.assertEquals("application/json;charset=UTF-8",
//				mvcResult.getResponse().getContentType());
	}
	
	@Test (expected = NestedServletException.class)
	public void getOne_OrderNonExistent_Status204Returned() throws Exception {
//		MvcResult mvcResult = this.mockMvc
		when(orderService.getOne(999L)).thenThrow(ResourceNotAvailableException.class);
        //MvcResult mvcResult = 
        		MockMvcBuilders.standaloneSetup(orderRestController).build()
				.perform(get("/orders/999"))
				.andDo(print());
//				.andExpect(status().isNoContent())
//				.andReturn();
	}
	
	@Test
	public void getList_OrdersExist_OrdersReturned() throws Exception {
//		MvcResult mvcResult = this.mockMvc
		List<Order> orders = new ArrayList<>();
		Order order = new Order();
		order.setOrderId(1L);
		orders.add(order);
		order.setOrderId(2L);
		orders.add(order);

		when(orderService.getList()).thenReturn(orders);

		MvcResult mvcResult = MockMvcBuilders.standaloneSetup(orderRestController).build()
				.perform(get("/orders"))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
				.andReturn();
        System.out.println("### Json Response: " + mvcResult.getResponse().getContentAsString());
		
	    JSONArray jsonArray = JsonPath.read(mvcResult.getResponse().getContentAsString(),"$");
	    System.out.println("### Json Response Size: " + jsonArray.size());
	    Assert.assertTrue(jsonArray.size() == 2);
	}
	
	@Test
	public void search_FilterOk_OrdersReturned() throws Exception {
		List<Order> orders = new ArrayList<>();
		Order order = new Order();
		order.setOrderId(1L);
		orders.add(order);
		order.setOrderId(2L);
		orders.add(order);
		
		when(orderService.searchOrders(Mockito.any(OrderFilter.class), Mockito.any(BindingResult.class)))
			.thenReturn(orders);				

		//		MvcResult mvcResult = this.mockMvc
		MvcResult mvcResult = MockMvcBuilders.standaloneSetup(orderRestController).build()
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
	
	@Test (expected = NestedServletException.class)
	public void applyCoupon_CouponInvalid_ReturnException() throws Exception {
		Order order = new Order();
		String code = "ZZZZ";
		order.setOrderId(1L);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonObject = mapper.writeValueAsString(order);
	    System.out.println("### Json Object: " + jsonObject);
		
		when(orderService.previewCoupon(Mockito.any(Order.class), Mockito.eq(code))).thenThrow(ResourceNotUpdatedException.class);

		MvcResult mvcResult = MockMvcBuilders.standaloneSetup(orderRestController).build()
				.perform(patch("/orders/1")
						.param("couponId","ZZZZ")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.content(jsonObject))
				.andDo(print())				
//				.andExpect(status().isConflict())
//				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();
	    System.out.println("### Status applyCoupon: " + mvcResult.getResponse().getStatus());
	}
}
