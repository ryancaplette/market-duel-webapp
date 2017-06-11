package com.marketduel.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.mysql.cj.jdbc.MysqlDataSource;

@Configuration
public class DatabaseConfig {

	@Bean
	public DataSource dataSource() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setURL("jdbc:mysql://sweng500-db.cmvcqoa7soac.us-west-2.rds.amazonaws.com:3306/sweng_500_db");
		dataSource.setUser("dbmasteruser");
		dataSource.setPassword("sweng500*");
		return dataSource;
	}
	
	@Bean   
	public DataSourceTransactionManager txManager() {
	    return new DataSourceTransactionManager(dataSource());
	}
}
