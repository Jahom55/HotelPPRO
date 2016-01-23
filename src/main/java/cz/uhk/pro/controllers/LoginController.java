package cz.uhk.pro.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.HotelUpload;
import cz.uhk.pro.model.Review;
import cz.uhk.pro.model.Role;
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

@Controller
public class LoginController {
	
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
	 
	 @Autowired
	 private HttpServletRequest context;
	

	@RequestMapping(value = {"/welcome**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security + Hibernate Example");
		model.addObject("message", "This is default page!");
		model.setViewName("hello");
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Spring Security + Hibernate Example");
		model.addObject("message", "This page is for ROLE_ADMIN only!");
		model.setViewName("admin");

		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}

		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}
	
	@RequestMapping(value="/registration")
	public String addUser(Model model){		
		List<Role> roleList = getAllWithoutAdmin();
		model.addAttribute("roles", roleList);
		User u = new User();
		model.addAttribute("user", u);
		return "registration";		
	}
	
	@RequestMapping(value="/updateUser", method = RequestMethod.POST)
	public String addUserProcess(Model model, @ModelAttribute("user") User u){	
		addressService.saveOrUpdate(u.getAddress());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		//hashujeme
		userService.saveOrUpdate(u);				
		return "redirect:/";
	}
	
	
	
	

	private List<Role> getAllWithoutAdmin() {
		List<Role> roleList = roleService.getAll();
		List<Role> roleListSelect = new ArrayList<Role>();
		for (Role role : roleList) {
			if (role.getRoleId() != 1) {
				if(role.getDescription().equals("ROLE_USER")) role.setDescription("Návštìvník");
				if(role.getDescription().equals("ROLE_HOTELIER")) role.setDescription("Hoteliér");
				roleListSelect.add(role);
			}
		}
		return roleListSelect;
	}

}