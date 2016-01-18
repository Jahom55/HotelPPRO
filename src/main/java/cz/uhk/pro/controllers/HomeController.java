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
import org.springframework.web.bind.annotation.SessionAttributes;

import cz.uhk.pro.model.Address;
import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Person;
import cz.uhk.pro.model.Review;
import cz.uhk.pro.model.Type;
import cz.uhk.pro.model.User;
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
		List<Hotel> hotelList = hotelService.getAll();		
		model.addAttribute("hotels", hotelList);
		
		//Hotel hotel = hotelService.get(1);
		
		//double d = hotelService.getRate(hotel);
		return "home";
	}
	
	@ModelAttribute("allHotels")
	public List<Hotel> populateHotels() {
	    return this.hotelService.getAll();
	}


/*	@RequestMapping(value = "/remove")
	public String remove(@RequestParam int id, @ModelAttribute Hotel hotel) {		
        Hotel p = hotelService.get(id);
        hotelService.remove(p);
		return "redirect:/";
	}*/
	
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
	
	@RequestMapping(value="/hotel")
	public String addHotel(Model model){		
		List<Type> typesList = typeService.getAll();
		model.addAttribute("types", typesList);
		Hotel h = new Hotel();
		model.addAttribute("hotel", h);
		Address a = new Address();
		model.addAttribute("address", a);
		return "hotelAddEdit";
	}
	
	@RequestMapping(value="/hotel", params = "id")
	public String editHotel(Model model, @RequestParam int id){		
		List<Type> typesList = typeService.getAll();
		model.addAttribute("types", typesList);
        Hotel h = hotelService.get(id);
        model.addAttribute("hotel", h);
        model.addAttribute("address",h.getAddress());
        model.addAttribute("equipment",h.getEquipment());
		return "hotelAddEdit";
	}
	@RequestMapping(value="/detail", params = "id")
	public String detailHotel(Model model, @RequestParam int id){		
		List<Review> reviewList = reviewService.getAll();
		List<Review> forhotel = null;
		for (Review review : reviewList) {
			if(review.getHotel().getHotelId() == id){
				forhotel.add(review);
				
			}
		}
		Hotel h = hotelService.get(id);
		model.addAttribute("type", h.getType());
		model.addAttribute("reviews", forhotel);
		model.addAttribute("hotel", h);
        model.addAttribute("address",h.getAddress());
        model.addAttribute("equipment",h.getEquipment());

		return "hotelDetail";
	}
	
	
	@RequestMapping(value = "/updateHotel", method = RequestMethod.POST)
	public String updateHotel(@ModelAttribute("hotel") Hotel h,
			Model model) { 
			addressService.saveOrUpdate(h.getAddress());
			equipmentService.saveOrUpdate(h.getEquipment());
			hotelService.saveOrUpdate(h);
			return "redirect:/";
	}
	
	@RequestMapping(value = "/remove")
	public String removeHotel(@RequestParam int id, @ModelAttribute Hotel hotel) {		
        Hotel p = hotelService.get(id);
        hotelService.remove(p);
		return "redirect:/";
	}
	
	
	
	
	
	
}
