package com.cp5;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class Logg {
	
	Logger log = LoggerFactory.getLogger(Logg.class);
	@Autowired
	
	private CustomerDao customerDao;

@Before("execution(public String deleteCustomer(..))")

public void capLog(JoinPoint jp) {
		Object oobj = jp.getArgs()[0];
		Long light = Long.parseLong(oobj.toString());
				System.out.println(light);
				Customer customer = customerDao.getCustomerById(light);
				//log.info(customer.toString());	
				System.out.println("Request to delete Customer with Name: " + customer.getCustName() + "with an customer ID of " + light);
}

	@Before("execution(public String showFormForUpdate(..))")
	  public void doLog(JoinPoint jp) {
			Object obj = jp.getArgs()[0];
			Long lid = Long.parseLong(obj.toString());
			Customer custUpdate = customerDao.getCustomerById(lid);
			log.info(" update Customer details  :" +custUpdate.getCustName() + ", with customer Id of " + lid + " has been updated.");
	}	
		
	@After("execution(public String  saveCustomer(..))")
	public void DologSave (JoinPoint jp) {
		Object obj = jp.getArgs()[0];
		log.info("New Customer Saved: " + obj.toString());
		
	}
	
	
	@Around("execution(public String showNewCustomerForm(..))")
	
	public Object NewLog(ProceedingJoinPoint joinPoint) throws Throwable  {
		try {
			System.out.println("New Form Page");
			Object don = joinPoint.proceed();
			System.out.println("Form Ended");
			return don;
			
		}
		
		catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
		
	}
	}


		


