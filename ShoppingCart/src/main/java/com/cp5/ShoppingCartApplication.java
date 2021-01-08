package com.cp5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class ShoppingCartApplication {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

		Product s21 = context.getBean("smartPhone", Product.class);
		Product g14 = context.getBean("gamingLaptop", Product.class);
		Product ipad = context.getBean("iPad", Product.class);

		ShoppingCart myCart = context.getBean("shoppingCart",ShoppingCart.class);
		myCart.addItem(ipad);
		
		System.out.println( " My Shopping Cart " + myCart.getItems());
		
		ShoppingCart urCart = context.getBean(ShoppingCart.class);
		urCart.addItem(s21);
		
		System.out.println( " Your Shopping Cart " + urCart.getItems());

		/**
		 * 
		 *  Every time you create a new POJO - do you have to make an entry into 
		 *  SpringConfig - NO 
		 *  
		 *  All Beans by default are Shared Singleton Objects ----  
		 * 
		 * 
		 */

	}
}
