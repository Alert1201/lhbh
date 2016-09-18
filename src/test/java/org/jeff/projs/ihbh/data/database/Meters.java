package org.jeff.projs.ihbh.data.database;

import org.jeff.projs.ihbh.data.daos.impl.MySqlMeterDaoImpl;
import org.jeff.projs.ihbh.data.domains.MeterDto;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class Meters {
	
	
	public static void main(String[] args) throws MySQLIntegrityConstraintViolationException{
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"test-application-context.xml");
		MySqlMeterDaoImpl meterDaoImpl = ctx
				.getBean(MySqlMeterDaoImpl.class);
		meterDaoImpl.deleteAll();
		meterDaoImpl.add(new MeterDto("8.8.8.8", "Long"));
		meterDaoImpl.add(new MeterDto("9.9.9.9", "Long"));
		meterDaoImpl.add(new MeterDto("10.10.10.10", "Long"));
		meterDaoImpl.add(new MeterDto("7.7.7.7", "Long"));
		meterDaoImpl.add(new MeterDto("11.11.11.11", "Long"));
		meterDaoImpl.add(new MeterDto("4.4.4.4", "Common"));
		meterDaoImpl.add(new MeterDto("2.2.2.2,1", "Common"));
		meterDaoImpl.add(new MeterDto("4.2.2.4", "Common"));
		meterDaoImpl.add(new MeterDto("2.4.4.2", "Common"));
		meterDaoImpl.add(new MeterDto("2.2.2.2", "Short"));
		meterDaoImpl.add(new MeterDto("1.1.1.1", "Short"));
		meterDaoImpl.add(new MeterDto("3.3.3.3", "Short"));
		meterDaoImpl.add(new MeterDto("4.2", "Short"));
		meterDaoImpl.add(new MeterDto("2.1.2.1", "Short"));
		meterDaoImpl.add(new MeterDto("3.2.1.2", "Short"));
		
		ctx = null;
	}
}
