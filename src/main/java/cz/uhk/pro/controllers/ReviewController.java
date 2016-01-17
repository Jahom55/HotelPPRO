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

import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Person;
import cz.uhk.pro.model.Review;
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
@SessionAttributes(types = Review.class)
public class ReviewController {
	
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
	 @RequestMapping(value="/review", params = "hotelId")
		public String detail(Model model, @RequestParam int hotelId){		
	        model.addAttribute("hotelId",hotelId);
	        Review r = new Review();
			model.addAttribute(r);			
			return "reviewAddEdit";
		}		
		
		@RequestMapping(value = "/updateReview",  params = "hotelId", method = RequestMethod.POST)
		public String updateUser(@ModelAttribute("review") Review r,@RequestParam int hotelId,
				Model model) {
				Hotel hotel = hotelService.get(hotelId);
				r.setUser(userService.get(1));
				r.setHotel(hotel);
				reviewService.saveOrUpdate(r);
				hotel.setRating(hotelService.getRate(hotel));
				hotelService.saveOrUpdate(hotel);					
				return "redirect:/";
		}
	
	
	
	
	
	
	
	
}
