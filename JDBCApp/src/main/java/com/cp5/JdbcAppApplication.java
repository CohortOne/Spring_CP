package com.cp5;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class JdbcAppApplication {

	public static void main(String[] args) {
		
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		
		VehicleDao vDao = context.getBean(VehicleDao.class);
		Vehicle v = new Vehicle("VBG3458", "Blue", 4, 4);
		vDao.insert(v);
		//vDao.update(v);
		System.out.println( " Vehicle VBG3456 has the color " + vDao.getColor("VBG3456"));
		System.out.println( " Vehicle Details  " + vDao.getVehicleCount());
		List <Vehicle> vehicles = vDao.findAll();
		vehicles.forEach(System.out::println);
		//vDao.delete("VBG3457");
	}
}