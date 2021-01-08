package com.cp5;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ShoppingCart {

	private List<Product> items = new ArrayList<>();
	
	public ShoppingCart() {
		System.out.println(" ==================================== ");
		System.out.println( " From Zero Arg Constructor of SCart ");
		System.out.println(" ==================================== ");
	}
	
	public void addItem(Product item) {
		items.add(item);
	}
	
	public List<Product> getItems(){
		return items;
	}
}
