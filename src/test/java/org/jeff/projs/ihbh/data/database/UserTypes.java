package org.jeff.projs.ihbh.data.database;

import org.jeff.projs.ihbh.data.daos.impl.MySqlUserTypeDaoImpl;
import org.jeff.projs.ihbh.data.domains.UserTypeDto;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTypes {
	
	
	public static void main(String[] args){
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"test-application-context.xml");
		MySqlUserTypeDaoImpl userTypeDaoImpl = ctx
				.getBean(MySqlUserTypeDaoImpl.class);
		userTypeDaoImpl.deleteAll();		
		userTypeDaoImpl.add(new UserTypeDto("Administrator", "Admin"));
		userTypeDaoImpl.add(new UserTypeDto("SuperUser", "Super"));
		userTypeDaoImpl.add(new UserTypeDto("Normal User", "User"));
		userTypeDaoImpl.add(new UserTypeDto("GuestUser", "Guest"));
		ctx = null;
	}
}
