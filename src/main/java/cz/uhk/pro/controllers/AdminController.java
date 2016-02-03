package cz.uhk.pro.controllers;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Review;
import cz.uhk.pro.model.User;
import cz.uhk.pro.service.HotelService;
import cz.uhk.pro.service.ReviewService;
import cz.uhk.pro.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired(required = true)
	private UserService userService;
	
	@Autowired(required = true)
	private HotelService hotelService;
	
	@Autowired(required = true)
	private ReviewService reviewService;

	@RequestMapping(value = "/admin/users", method = RequestMethod.GET)
	public String allUsers(Locale locale, Model model) {
		List<User> userList = userService.getAll();
		model.addAttribute("users", userList);
		return "/admin/users";
	}

	@RequestMapping(value = "/admin/disabled")
	public String disabledUser(@RequestParam int id) {
		User u = userService.get(id);
		u.setEnabled(false);
		userService.saveOrUpdate(u);
		return "redirect:/admin/users";
	}

	@RequestMapping(value = "/admin/enabled")
	public String enabledUser(@RequestParam int id) {
		User u = userService.get(id);
		u.setEnabled(true);
		userService.saveOrUpdate(u);
		return "redirect:/admin/users";
	}
	
	@RequestMapping(value = "/admin/detailUser", params = "id")
	public String detailUserAdmin(Model model, @RequestParam int id) {
		User u = userService.get(id);
		List<Hotel> hotels = hotelService.getHotelsByUser(u);
		List<Review> reviews = reviewService.getReviewsByUser(u);
		model.addAttribute("hotels", hotels);
		model.addAttribute("reviews", reviews);
		model.addAttribute("user", u);
		model.addAttribute("address", u.getAddress());
		model.addAttribute("district", u.getAddress().getDistrict());
		return "userDetail";
	}

}
