package Springcar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
	@Autowired
	private CarRepository repo;
	
	public List<Cars> listAll() {		
		return repo.findAll();
	}
	
	public void save(Cars product) {
		repo.save(product);
	}
	
	public Cars get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
