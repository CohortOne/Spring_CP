package com.cp5;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class SpringConfig {

	@Bean 
	public VehicleDao vehicleDao() {
		return new JdbcVehicleDao(dataSource());
	}

	private DataSource dataSource() {
		DriverManagerDataSource dmDS = new DriverManagerDataSource();
		dmDS.setDriverClassName("oracle.jdbc.OracleDriver");
		dmDS.setUrl("jdbc:oracle:thin:@//localhost:1521/XEPDB1");
		dmDS.setUsername("hr");
		dmDS.setPassword("hr");
		return dmDS;
	}
}
