package com.softtek.javaweb.main;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.softtek.javaweb.domain.model.User;
import com.softtek.javaweb.domain.model.UserRole;
import com.softtek.javaweb.service.StatusService;
import com.softtek.javaweb.service.UserRoleService;
import com.softtek.javaweb.service.UserService;

public class JavaWebMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/META-INF/jdbc-applicationContext.xml");
		UserService userService = applicationContext.getBean(UserService.class);
		UserRoleService userRoleService = applicationContext.getBean(UserRoleService.class);
		StatusService statusService = applicationContext.getBean(StatusService.class);
		System.out.println ("### Get one status with service : " + statusService.getOne(1100L));
		System.out.println ("### Get one user role with service : " + userRoleService.getOne("ADM"));
		System.out.println ("### Get one user with service : " + userService.getOne("admin"));
}

}
