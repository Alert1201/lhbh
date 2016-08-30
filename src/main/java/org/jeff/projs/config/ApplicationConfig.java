package org.jeff.projs.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.jeff.projs.ihbh.data.daos.impl.MySqlAuthorDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlCategoryDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlHymnDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlHymnalDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlLookupDAOImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlMeterDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlSaveStateDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlTuneDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlUserDaoImpl;
import org.jeff.projs.ihbh.data.daos.impl.MySqlUserTypeDaoImpl;
import org.jeff.projs.ihbh.services.impl.LookupServiceImpl;
import org.jeff.projs.ihbh.services.impl.MeterServiceImpl;
import org.jeff.projs.web.controller.admin.MetersController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;


@Configuration
@ComponentScan(basePackages = { "org.jeff.projs.ihbh.data.daos",
		"org.jeff.projs.ihbh.services", "org.jeff.projs.web.controller" })

public class  ApplicationConfig {

	 @Bean 
	   public MetersController metersController(){
	      return new MetersController();
	   }
	 
	 @Bean 
	   public MeterServiceImpl meterServiceImpl(){
	      return new MeterServiceImpl();
	   }
	
	 @Bean 
	   public MySqlAuthorDaoImpl mySqlAuthorDaoImpl(){
	      return new MySqlAuthorDaoImpl();
	   }
	 
	 @Bean 
	   public MySqlCategoryDaoImpl mySqlCategoryDaoImpl(){
	      return new MySqlCategoryDaoImpl();
	   }

	 @Bean 
	   public MySqlHymnalDaoImpl mySqlHymnalDaoImpl(){
	      return new MySqlHymnalDaoImpl();
	   }
	 
	 @Bean 
	   public MySqlHymnDaoImpl mySqlHymnDaoImpl(){
	      return new MySqlHymnDaoImpl();
	   }
	 
	 @Bean 
	   public MySqlMeterDaoImpl mySqlMeterDaoImpl(){
	      return new MySqlMeterDaoImpl();
	   }
	 
	 @Bean 
	   public  MySqlSaveStateDaoImpl mySqlSaveStateDaoImpl(){
	      return new MySqlSaveStateDaoImpl();
	   }
	 
	 @Bean 
	   public MySqlTuneDaoImpl mySqlTuneDaoImpl(){
	      return new MySqlTuneDaoImpl();
	   }
	 
	 @Bean 
	   public  MySqlUserDaoImpl mySqlUserDaoImpl(){
	      return new MySqlUserDaoImpl();
	   }
	
	 @Bean 
	   public  MySqlUserTypeDaoImpl mySqlUserTypeDaoImpl(){
	      return new MySqlUserTypeDaoImpl();
	   }
	 
	 @Bean 
	   public  MySqlLookupDAOImpl mySqlLookupDAOImpl(){
	      return new MySqlLookupDAOImpl();
	   }
	 
	 @Bean 
	   public  LookupServiceImpl  lookupServiceImpl(){
	      return new LookupServiceImpl();
	   }
	 
	 @Bean(name = "dataSource")
	public DataSource dataSource() {
		BasicDataSource datasource = new BasicDataSource();
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/testhymnparts");
		datasource.setUsername("root");
		datasource.setPassword("");
		datasource.setInitialSize(2);
		datasource.setMaxTotal(5);
		return datasource;
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

}
