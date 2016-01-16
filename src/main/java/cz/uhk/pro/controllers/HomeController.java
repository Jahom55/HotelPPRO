package cz.uhk.pro.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cz.uhk.pro.model.Person;
import cz.uhk.pro.service.AddressService;
import cz.uhk.pro.service.EquipmentService;
import cz.uhk.pro.service.HotelService;
import cz.uhk.pro.service.ImageService;
import cz.uhk.pro.service.PersonService;
import cz.uhk.pro.service.ReviewService;
import cz.uhk.pro.service.RoleService;
import cz.uhk.pro.service.TypeService;
import cz.uhk.pro.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	 @Autowired(required = true)
	 private PersonService personService;

	 @Autowired(required = true)
	 private AddressService addressService;

	 @Autowired(required = true)
	 private RoleService roleService;
	 
	 @Autowired(required = true)
	 private TypeService typeService;
	 
	 @Autowired(required = true)
	 private UserService userService;
	 
	 @Autowired(required = true)
	 private EquipmentService equipmentService;
	 
	 @Autowired(required = true)
	 private HotelService hotelService;
	 
	 @Autowired(required = true)
	 private ImageService imageService;
	 
	 @Autowired(required = true)
	 private ReviewService reviewService;
	 

	 

	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {		
		List<Person> personList = personService.getAll();		
		model.addAttribute("persons", personList);			
		return "home";
	}
	
	@RequestMapping(value = "/remove")
	public String remove(@RequestParam int id, @ModelAttribute Person person) {		
        Person p = personService.get(id);
        personService.remove(p);
		return "redirect:/";
	}
	
	@RequestMapping(value="/person")
	public String addPerson(Model model){		
		Person p = new Person();
		model.addAttribute(p);
		return "person";
	}
	@RequestMapping(value="/person", params = "id")
	public String detail(Model model, @RequestParam int id){		
        Person p = personService.get(id);        
        model.addAttribute("person", p);
		return "person";
	}
	
	
	@RequestMapping(value = "/updatePerson", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("person") Person p,
			Model model) {        
			personService.saveOrUpdate(p);
			return "redirect:/";

	

	}
	
	
	
	
	
	
}
