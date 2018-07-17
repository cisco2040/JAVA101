package com.softtek.javaweb.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.softtek.javaweb.service.*;

public class JavaWebMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/META-INF/applicationContext.xml");
        
		String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println("###### Beans: " + beanName + " -- " + applicationContext.getBean(beanName));
        }
		
//		CartService cartService = applicationContext.getBean(CartService.class);
//		CityService cityService = applicationContext.getBean(CityService.class);
//		ShippingZoneService shippingZoneService = applicationContext.getBean(ShippingZoneService.class);
//		ShipToService shipToService = applicationContext.getBean(ShipToService.class);
//		StateService stateService = applicationContext.getBean(StateService.class);
//		StatusService statusService = applicationContext.getBean(StatusService.class);
//		UserRoleService userRoleService = applicationContext.getBean(UserRoleService.class);
//		UserService userService = applicationContext.getBean(UserService.class);
//		System.out.println ("### Get one Cart with service : " + cartService.getOne(1L));
//		System.out.println ("### Get several Carts with service : " + cartService.getList());
//		System.out.println ("### Get one City with service : " + cityService.getOne(1L));
//		System.out.println ("### Get one Shipping Zone with service : " + shippingZoneService.getOne("CT"));
//		System.out.println ("### Get one ShipTo with service : " + shipToService.getOne(1L));
//		System.out.println ("### Get one State with service : " + stateService.getOne(1L));
//		System.out.println ("### Get one Status with service : " + statusService.getOne(1100L));
//		System.out.println ("### Get one User Role with service : " + userRoleService.getOne("ADM"));
//		System.out.println ("### Get one User with service : " + userService.getOne("admin"));
	}
}
