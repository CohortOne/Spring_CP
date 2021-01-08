package com.cp5;

public class Mobile extends Product {

	private String maker;
	
	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public Mobile() {
		// TODO Auto-generated constructor stub
	}

	public Mobile(String name, double price) {
		super(name, price);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Product [name=" + this.getName() + ", price=" + this.getPrice() + "]" + " Mobile [maker=" + maker + "]";
	}

}
