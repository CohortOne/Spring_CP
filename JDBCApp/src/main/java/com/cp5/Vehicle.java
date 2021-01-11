package com.cp5;

public class Vehicle {

	private String vehicleNo;
	private String color;
	private int seat;
	private int wheel;
	
	public Vehicle() {
		
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public int getWheel() {
		return wheel;
	}

	public void setWheel(int wheel) {
		this.wheel = wheel;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleNo=" + vehicleNo + ", color=" + color + ", seat=" + seat + ", wheel=" + wheel + "]";
	}

	public Vehicle(String vehicleNo, String color, int seat, int wheel) {
		super();
		this.vehicleNo = vehicleNo;
		this.color = color;
		this.seat = seat;
		this.wheel = wheel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + seat;
		result = prime * result + ((vehicleNo == null) ? 0 : vehicleNo.hashCode());
		result = prime * result + wheel;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (seat != other.seat)
			return false;
		if (vehicleNo == null) {
			if (other.vehicleNo != null)
				return false;
		} else if (!vehicleNo.equals(other.vehicleNo))
			return false;
		if (wheel != other.wheel)
			return false;
		return true;
	}
}
