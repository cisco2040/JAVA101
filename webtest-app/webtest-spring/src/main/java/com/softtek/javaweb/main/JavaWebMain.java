package com.softtek.javaweb.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.softtek.javaweb.service.ShippingZoneService;
import com.softtek.javaweb.service.StatusService;
import com.softtek.javaweb.service.UserRoleService;
import com.softtek.javaweb.service.UserService;

public class JavaWebMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/META-INF/jdbc-applicationContext.xml");
        
		String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println("###### Beans: " + beanName + " -- " + applicationContext.getBean(beanName));
        }
		
		StatusService statusService = applicationContext.getBean(StatusService.class);
		UserRoleService userRoleService = applicationContext.getBean(UserRoleService.class);
		ShippingZoneService shippingZoneService = applicationContext.getBean(ShippingZoneService.class);
		UserService userService = applicationContext.getBean(UserService.class);
		
		System.out.println ("### Get one Status with service : " + statusService.getOne(1100L));
		System.out.println ("### Get one User role with service : " + userRoleService.getOne("ADM"));
		System.out.println ("### Get one Shipping Zone with service : " + shippingZoneService.getOne("CT"));
		System.out.println ("### Get one User with service : " + userService.getOne("admin"));
	}
}
