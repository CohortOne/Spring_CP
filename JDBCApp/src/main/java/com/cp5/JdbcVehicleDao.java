package com.cp5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class JdbcVehicleDao implements VehicleDao {

	private static final String INSERT_SQL = "INSERT INTO HR.VEHICLE (COLOR, WHEEL, SEAT, VEHICLE_NO) VALUES (?,?,?,?)";
	private static final String UPDATE_SQL = "UPDATE HR.VEHICLE SET COLOR=?, WHEEL=?, SEAT=? WHERE VEHICLE_NO=?";
	private static final String SELECT_ALL_SQL = "SELECT * FROM HR.VEHICLE";
	private static final String SELECT_ONE_SQL = "SELECT * FROM HR.VEHICLE WHERE VEHICLE_NO=?";
	private static final String DELETE_SQL = "DELETE FROM HR.VEHICLE WHERE VEHICLE_NO=?";
	private static final String COUNT_ALL = "SELECT COUNT(*) FROM HR.VEHICLE";
	private static final String GET_COLOR = "SELECT COLOR FROM HR.VEHICLE WHERE VEHICLE_NO = ?"; 
	
	private final DataSource ds;
	
	public JdbcVehicleDao(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public void insert(Vehicle vehicle) {
		try( Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(INSERT_SQL)){
				prepareStmt(vehicle, pstmt);
				pstmt.executeUpdate();
		}catch(Exception e) {
			System.out.println(" Exception in insert op :: " + e.getMessage() );
			e.printStackTrace();
		}
	}

	private void prepareStmt(Vehicle vehicle, PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, vehicle.getColor());
		pstmt.setInt(2, vehicle.getWheel());
		pstmt.setInt(3, vehicle.getSeat());
		pstmt.setString(4, vehicle.getVehicleNo());
	}

	@Override
	public void insert(Iterable<Vehicle> vehicles) {
		vehicles.forEach(this::insert);
	}

	@Override
	public void update(Vehicle vehicle) {
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(UPDATE_SQL)){
			prepareStmt(vehicle, ps);
			ps.executeUpdate();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(String vehicleNo) {
		try (Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(DELETE_SQL)){
			ps.setString(1, vehicleNo);
			ps.executeUpdate();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	public Vehicle findByVehicleNo(String vehicleNo) {
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(SELECT_ONE_SQL)){
			ps.setString(1, vehicleNo);
			Vehicle vehicle = null;
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next())
					vehicle = toVehicle(rs);
			}
			return vehicle;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private Vehicle toVehicle(ResultSet rs) throws Exception{
		
		return new Vehicle(rs.getString("VEHICLE_NO"), rs.getString("COLOR"),
				rs.getInt("SEAT"), rs.getInt("WHEEL"));
	}

	@Override
	public List<Vehicle> findAll() {
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(SELECT_ALL_SQL)){
			List <Vehicle> vehicles = new ArrayList<>();
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next())
					vehicles.add(toVehicle(rs));
			}
			return vehicles;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getColor(String vehicleNo) {
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(GET_COLOR)){
			ps.setString(1, vehicleNo);
			String color = null;
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next())
					color = rs.getString("COLOR");
			}
			return color;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getVehicleCount() {
		try(Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(COUNT_ALL)){
			
			int count = 0;
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next())
					count = rs.getInt(1);
			}
			return count;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
