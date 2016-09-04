package org.jeff.projs.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.jeff.projs.ihbh.services.impl.MeterServiceImpl;
import org.jeff.projs.web.controller.admin.MetersController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;


@Configuration
/*@ComponentScan(basePackages = { "org.jeff.projs.ihbh.data.daos",
		"org.jeff.projs.ihbh.services", "org.jeff.projs.web.controller" })*/
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	 @Bean 
	   public MetersController metersController(){
	      return new MetersController();
	   }
	 
	 @Bean 
	   public MeterServiceImpl meterServiceImpl(){
	      return new MeterServiceImpl();
	   }
	
	@Bean
	TilesViewResolver viewResolver() {
		TilesViewResolver viewResolver = new TilesViewResolver();
		return viewResolver;
	}

	@Bean
	TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("WEB-INF/views/jsp/tiles/tiles.xml",
				"WEB-INF/views/jsp/tiles/admin.xml");
		tilesConfigurer
				.setPreparerFactoryClass(org.springframework.web.servlet.view.tiles3.SpringBeanPreparerFactory.class);
		return tilesConfigurer;
	}

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

/*	@Bean(name = "dataSource")
	public DataSource dataSource() {
		BasicDataSource datasource = new BasicDataSource();
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/testhymnparts");
		datasource.setUsername("root");
		datasource.setPassword("");
		datasource.setInitialSize(2);
		datasource.setMaxTotal(5);
		return datasource;
	}*/
	
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		BasicDataSource datasource = new BasicDataSource();
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://97.74.31.62:3306/testlhbp");
		datasource.setUsername("testlhbp");
		datasource.setPassword("Brunner@1");
		datasource.setInitialSize(2);
		datasource.setMaxTotal(5);
		return datasource;
	}

	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}

}
