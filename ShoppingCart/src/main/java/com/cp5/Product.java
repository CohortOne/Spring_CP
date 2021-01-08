package com.cp5;

public abstract class Product {
	
	private String name;
	private double price;

	public Product() {}

	public Product(String name, double price) {

		super();
		System.out.println(" ==================================== ");
		System.out.println( " From 2 Arg Constructor of Product ");
		System.out.println(" ==================================== ");

		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + "]";
	}

	
}
