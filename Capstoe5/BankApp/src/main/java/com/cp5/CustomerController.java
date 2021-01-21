package com.cp5;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {

	@Autowired
	private CustomerDao customerDao;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		//model.addAttribute("listCustomers", customerDao.getAllCustomers());
		return findPaginated(1, "custId", "ASC",  model);
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, 
											@RequestParam("sortField") String sortField, 
											@RequestParam("sortDirection") String sortDirection, 
																 Model model) {
		
		int pageSize = 5;
		Page <Customer> page = customerDao.findPaginated(pageNo, pageSize, sortField, sortDirection);
		List <Customer> listCustomers = page.getContent();
		model.addAttribute("listCustomers", listCustomers);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", sortDirection.equalsIgnoreCase("ASC") ? "DESC" : "ASC");
		return "index";
	}

	@GetMapping("/showNewCustomerForm")
	public String showNewCustomerForm(Model model) {
		Customer customer =new Customer();
		model.addAttribute("customer", customer);
		return "new_customer";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
		if(bindingResult.hasErrors())
			//System.out.println(bindingResult.getAllErrors());
			return "new_customer";
		customerDao.saveCustomer(customer);
		return "redirect:/";
	}

	@GetMapping("/showFormForUpdate/{custId}")
	public String showFormForUpdate(@PathVariable(value = "custId") long custId, Model model) {
		// Get Employee from the Service 
		Customer customer = customerDao.getCustomerById(custId);
		
		// set employee as a model attribute to pre-populate the form 
		model.addAttribute("customer", customer);
		return "update_customer";
	}
	
	@GetMapping("/deleteCustomer/{custId}")
	public String deleteCustomer(@PathVariable (value = "custId") long custId) {
	 // call delete employee method 
	 this.customerDao.deleteCustomerById(custId);
	 return "redirect:/";
	}
}
