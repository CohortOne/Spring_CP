package Springcar;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	@Autowired
	private CarService service;
	@Autowired
	private CarStatusRepo carSttsRepo;
	
	@GetMapping("/newCarStatus")
	public String newVehStatus(Model model) {
		CarStatus vehStatus = new CarStatus( );
		model.addAttribute("vehStatus", vehStatus);
		//log.info("======> new Role");
		return "newCarStatus";
	}
	
	@PostMapping("/carStatusSave")
	public String saveVehStatus(@Valid @ModelAttribute CarStatus carStatus) {
		carSttsRepo.save(carStatus);
		return "redirect:/";
	}
		
		@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Cars> listProducts = service.listAll();
		model.addAttribute("listProducts", listProducts);
		//session.setAttribute("favcolor", "blue");
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewProductForm(Model model) {
		Cars product = new Cars();
		model.addAttribute("product", product);
		
		return "new_car";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Cars product) {
		service.save(product);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_car");
		
		Cars product = service.get(id);
		mav.addObject("product", product);
		
		return mav;
	}	
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/";
	}
}
