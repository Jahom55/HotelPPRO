package cz.uhk.pro.controllers;

import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cz.uhk.pro.model.User;
import cz.uhk.pro.service.AddressService;
import cz.uhk.pro.service.DistrictService;
import cz.uhk.pro.service.EquipmentService;
import cz.uhk.pro.service.HotelService;
import cz.uhk.pro.service.ImageService;
import cz.uhk.pro.service.ReviewService;
import cz.uhk.pro.service.RoleService;
import cz.uhk.pro.service.TypeService;
import cz.uhk.pro.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired(required = true)
	private UserService userService;

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

}
