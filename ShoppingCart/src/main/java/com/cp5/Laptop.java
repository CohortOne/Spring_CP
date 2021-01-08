package com.cp5;

public class Laptop extends Product {

	private String osType;
	
	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public Laptop() {
		super();
	}

	public Laptop(String name, double price) {
		super(name, price);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Product [name=" + this.getName() + ", price=" + this.getPrice() + "]" + " Laptop [osType=" + osType + "]";
	}
}
