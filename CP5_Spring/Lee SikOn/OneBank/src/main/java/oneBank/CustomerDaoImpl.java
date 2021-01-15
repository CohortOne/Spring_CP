package oneBank;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service  //
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerById(long custId) {
		Optional <Customer> optional = customerRepository.findById(custId);
		Customer customer = null;
		if (optional.isPresent())
			customer = optional.get();
		else
			throw new RuntimeException("Customer not found for id=" + custId);
		return customer;
	}

	@Override
	public void deleteCustomerById(long custId) {
		customerRepository.deleteById(custId);
	}

	@Override
	public Page<Customer> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort); // Note pageNo starts from 0, not 1, hence the -1.
		return customerRepository.findAll(pageable);
	}

}
