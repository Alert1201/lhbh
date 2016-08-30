package org.jeff.projs.ihbh.data.database;

import org.jeff.projs.ihbh.data.daos.impl.MySqlAuthorDaoImpl;
import org.jeff.projs.ihbh.data.domains.AuthorDto;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Authors {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"test-application-context.xml");
		MySqlAuthorDaoImpl authorsDaoImpl = ctx.getBean(MySqlAuthorDaoImpl.class);
		authorsDaoImpl.deleteAll();
		authorsDaoImpl.add(new AuthorDto("Jeff", "Sulman","11","1670","01","1710","British"));
		authorsDaoImpl.add(new AuthorDto("Bill", "Bullins","01","1900","04","1960","Mexican"));
		authorsDaoImpl.add(new AuthorDto("Linda", "Spindle","02","1890","09","1940","American"));
		authorsDaoImpl.add(new AuthorDto("Janet", "Dickens","03","1770","10","1830","Russian"));
		authorsDaoImpl.add(new AuthorDto("Bruce", "Lee","04","1567","10","1621","Bosnian"));
		authorsDaoImpl.add(new AuthorDto("Marven", "Gay","05","1544","11","1600","Canadian"));
		authorsDaoImpl.add(new AuthorDto("Betty", "Young","06","1700","07","1766","Indian"));
	}
}
