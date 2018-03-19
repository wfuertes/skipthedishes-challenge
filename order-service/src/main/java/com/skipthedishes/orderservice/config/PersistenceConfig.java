package com.skipthedishes.orderservice.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class PersistenceConfig {
	
	@Bean
	public DataSource getDataSource() {

		HikariDataSource ds = new HikariDataSource();
		ds.setDriverClassName("org.h2.Driver");
		/*
		 * Aqui estamos rodando o script de inicializado do banco que esta no diretório
		 * "resources/scripts"
		 * INIT=RUNSCRIPT FROM 'classpath:scripts/schema.sql'
		 */
		ds.setJdbcUrl("jdbc:h2:mem:order_service;DB_CLOSE_DELAY=-1");
		ds.setUsername("sa");
		ds.setPassword("sa");
		ds.setConnectionTestQuery("SELECT 1");

		return ds;
	}

	@Bean("entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource) {

		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
		emfb.setDataSource(dataSource);
		emfb.setPackagesToScan("com.skipthedishes.orderservice.model");
		emfb.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		Map<String, Object> props = new HashMap<>();
		props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		props.put("hibernate.hbm2ddl.auto", "create-drop");
		props.put("hibernate.show_sql", true);
		props.put("hibernate.format_sql", true);
		props.put("auto-import", false);

		emfb.setJpaPropertyMap(props);

		return emfb;
	}
}
