package oneBank;

import java.util.List;
import java.util.Optional;

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

	@GetMapping("/") //this is the home page at root directory of the website to be triggered by a http GET. 
	public String viewHomePage(Model model) {
		Customer newCust = new Customer();
		newCust.setIsActive(true);
		newCust.setDateUpd(java.time.LocalDate.now());
		model.addAttribute("newCust", newCust);
		return findPaginated(1, 5, "custId", "ASC", model);
	}

	@GetMapping("/page/{pageNo}")
	private String findPaginated(@PathVariable(value="pageNo") int pageNo, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("sortField") String sortField, 
			@RequestParam("sortDirection") String sortDirection, Model model) {
		Page <Customer> page = customerDao.findPaginated(pageNo, pageSize, sortField, sortDirection);
		if (pageNo > page.getTotalPages()) {return findPaginated(page.getTotalPages(), pageSize, sortField, sortDirection, model);} 
		List <Customer> listCustomers = page.getContent();
		model.addAttribute("listCustomers", listCustomers);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("currPage", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		model.addAttribute("reverseSortDirection", sortDirection.equalsIgnoreCase("ASC")?"DESC":"ASC");
		Customer newCust = (Customer)model.getAttribute("newCust");
		if (newCust==null) {
			newCust = new Customer();
			newCust.setIsActive(true);
			newCust.setDateUpd(java.time.LocalDate.now());
			model.addAttribute("newCust", newCust);
		}
		return "index";
	}
	
	@GetMapping("/otsCustomerUpdate/{custId}")
	public String otsCustomerUpdate(@PathVariable(value = "custId") long custId, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("sortField") String sortField, 
			@RequestParam("sortDirection") String sortDirection, 
			@RequestParam("currPage") int currPage, Model model) {

		// Get customer from the Service
		Customer newCust = new Customer();
		if (custId == 0) {
			newCust.setIsActive(true);
		} else {
			if (custId > 0) {
				newCust = customerDao.getCustomerById(custId);
			} else {
				// the following won't work if you do this:
				// newCust = customerDao.getCustomerById(-custId);
				// newCust.setCustId(0);
				// newCust.setNric("");
				// newCust.setPassCode("");
				// because setters will also affect the customer object in listCustomers
				Customer customer = customerDao.getCustomerById(-custId);
				newCust = new Customer();
				newCust.setCustName(customer.getCustName());
				newCust.setPhoneNo(customer.getPhoneNo());
				newCust.setEmail(customer.getEmail());
				newCust.setAddr1(customer.getAddr1());
				newCust.setAddr2(customer.getAddr2());
				newCust.setDob(customer.getDob());
				newCust.setIsActive(customer.getIsActive());
			}
		}
		newCust.setDateUpd(java.time.LocalDate.now());
		model.addAttribute("newCust", newCust);
		return findPaginated(currPage, pageSize, sortField, sortDirection, model);
	}

	@PostMapping("/saveCustomerOTS")	
	public String saveCustomerOTS(@Valid @ModelAttribute("newCust")Customer newCust, BindingResult bindingResult, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("sortField") String sortField, 
			@RequestParam("sortDirection") String sortDirection, 
			@RequestParam("currPage") int currPage, Model model) {

		if (!bindingResult.hasErrors()) {
			customerDao.saveCustomer(newCust);
			newCust = new Customer();
			model.addAttribute("newCust", newCust);
			return findPaginated(currPage, pageSize, sortField, sortDirection, model); 
		}
		model.addAttribute("newCust", newCust);
		return findPaginated(currPage, pageSize, sortField, sortDirection, model);
	}
	
	@GetMapping("/showCustomerForUpdate/{custId}")
	public String showCustomerForUpdate(@PathVariable(value = "custId") long custId, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("sortField") String sortField, 
			@RequestParam("sortDirection") String sortDirection, 
			@RequestParam("currPage") int currPage, Model model) {

		// Get customer from the Service
		Customer newCust = new Customer();
		if (custId == 0) {
			newCust.setIsActive(true);
			model.addAttribute("updMode", "New");
		} else {
			if (custId > 0) {
				newCust = customerDao.getCustomerById(custId);
				model.addAttribute("updMode", "Update");
			} else {
				newCust = customerDao.getCustomerById(-custId);
				newCust.setCustId(0);
				newCust.setNric("");
				newCust.setPassCode("");
				model.addAttribute("updMode", "Duplicate");
			}
		}
		newCust.setDateUpd(java.time.LocalDate.now());
		model.addAttribute("newCust", newCust);
		model.addAttribute("currPage", currPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		return "upd_customer";
	}
	
	
	@PostMapping("/saveCustomer")	
	public String saveCustomer(@Valid @ModelAttribute("newCust")Customer newCust, BindingResult bindingResult, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("sortField") String sortField, 
			@RequestParam("sortDirection") String sortDirection, 
			@RequestParam("updMode") String updMode, 
			@RequestParam("currPage") int currPage, Model model) {
		model.addAttribute("currPage", currPage);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDirection", sortDirection);
		if (bindingResult.hasErrors()) {
			model.addAttribute("updMode", updMode);
			return ("upd_customer");
			}
		customerDao.saveCustomer(newCust);
		model.addAttribute("newCust", null);
		return findPaginated(currPage, pageSize, sortField, sortDirection, model);
		//return "index"; // this will be referring to index.html at the templates folder
	}
	
	
	@GetMapping("/deleteCustomer/{custId}")
	public String deleteCustomer(@PathVariable(value = "custId") long custId,
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("sortField") String sortField, 
			@RequestParam("sortDirection") String sortDirection, 
			@RequestParam("currPage") int currPage, Model model) {
		// call delete customer method 
        this.customerDao.deleteCustomerById(custId);		
		return findPaginated(currPage, pageSize, sortField, sortDirection, model);
	}

	@GetMapping("/andCustomer/{custId}")
	public String andCustomer(@PathVariable(value = "custId") long custId,
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("sortField") String sortField, 
			@RequestParam("sortDirection") String sortDirection, 
			@RequestParam("currPage") int currPage, Model model) {
		// call update customer method
		Boolean activate = custId > 0; // +ve customer id to activate, -ve to deactivate
		if (!activate) custId = - custId;
		Customer customer = customerDao.getCustomerById(custId);
		customer.setIsActive(activate);
		customer.setDateUpd(java.time.LocalDate.now());
		this.customerDao.saveCustomer(customer);
		return findPaginated(currPage, pageSize, sortField, sortDirection, model);
	}
}
