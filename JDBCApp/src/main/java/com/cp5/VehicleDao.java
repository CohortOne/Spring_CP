package com.cp5;

import java.util.List;

public interface VehicleDao {
	
	public void insert(Vehicle vehicle);
	public void insert(Iterable<Vehicle> vehicle);
	public void delete(String vehicleNo);
	public void update(Vehicle vehicle);
	public Vehicle findByVehicleNo(String vehicleNo);
	public List<Vehicle> findAll();
	public String getColor(String vehicleNo);
	public int getVehicleCount();
}

// ORM Frameworks - Hibernate / JPA
//   store - UPSERT 
