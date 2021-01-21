package com.cp5;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public interface CustomerDao {
	public List<Customer> getAllCustomers();
	public void saveCustomer(Customer customer);
	public Customer getCustomerById(long custId);
	public void deleteCustomerById(long custId);
	public Page <Customer> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}