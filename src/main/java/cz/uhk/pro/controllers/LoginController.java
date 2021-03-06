package cz.uhk.pro.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cz.uhk.pro.model.Address;
import cz.uhk.pro.model.District;
import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.help.HotelUpload;
import cz.uhk.pro.model.Image;
import cz.uhk.pro.model.Review;
import cz.uhk.pro.model.Role;
import cz.uhk.pro.model.Type;
import cz.uhk.pro.model.User;
import cz.uhk.pro.service.AddressService;
import cz.uhk.pro.service.DistrictService;
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
	private AddressService addressService;

	@Autowired(required = true)
	private RoleService roleService;

	@Autowired(required = true)
	private UserService userService;


	@Autowired(required = true)
	private HotelService hotelService;

	@Autowired(required = true)
	private ReviewService reviewService;

	@Autowired(required = true)
	private DistrictService districtService;

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

	@RequestMapping(value = "/updateUserImages", method = RequestMethod.POST)
	public ResponseEntity uploadFile(MultipartHttpServletRequest request) {

		try {
			int id = Integer.valueOf(request.getParameter("id"));
			Iterator<String> itr = request.getFileNames();

			System.out.println(id);
			while (itr.hasNext()) {
				String path = "";
				String uploadedFile = itr.next();
				MultipartFile file = request.getFile(uploadedFile);

				String pType = file.getContentType().split("/")[0];
				String sType = file.getContentType().split("/")[1];
				System.out.println(sType);
				UUID uuid = new UUID(255, 200);
				String name = String.valueOf(uuid.randomUUID());
				System.out.println(name);
				String fileName = file.getOriginalFilename();
				if (fileName.length() > 15)
					fileName = fileName.substring(0, 15);
				fileName = String.valueOf(fileName.hashCode());
				System.out.println(fileName);
				path = "D:/sts/work/ProHotel/src/main/webapp/resources/images/" + name + fileName + "." + sType;
				File destination = new File(path);
				file.transferTo(destination);
				String path2 = "";
				path2 = "../../pro/resources/images/" + name + fileName + "." + sType;
				System.out.println(path2);
				User u = userService.get(id);
				u.setImage(path2);
				System.out.println(u.toString());
				File check = new File(path);
				userService.saveOrUpdate(u);
				System.out.println(u.toString());
			}
		} catch (Exception e) {
			return new ResponseEntity("{}", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		System.out.println("ok");
		return new ResponseEntity("{}", HttpStatus.OK);
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String addUser(Model model) {
		List<Role> roleList = getAllWithoutAdmin();
		List<District> districtList = districtService.getAll();
		model.addAttribute("districts", districtList);
		model.addAttribute("roles", roleList);
		User u = new User();
		u.setEmail("dummy@dummy.com");
		u.setLogin("dummy");
		u.setPassword("moredummy");
		u.setName("dummy");
		u.setSurname("dummy");
		int id = (int) userService.add(u);
		User user = new User();
		user.setUserId(id);
		model.addAttribute("user", user);
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String addUserProcess(Model model, @Valid @ModelAttribute("user") User u, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<Role> roleList = getAllWithoutAdmin();
			model.addAttribute("roles", roleList);
			u.setPassword(null);
			model.addAttribute("user", u);
			List<District> districtList = districtService.getAll();
			model.addAttribute("districts", districtList);
			return "registration";
		}
		User user = userService.get(u.getUserId());
		u.setImage(user.getImage());
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		u.setPassword(b.encode(u.getPassword()));
		Address a = new Address();
		a = u.getAddress();
		District d = districtService.get(a.getDistrict().getDistrictId());
		a.setDistrict(d);
		addressService.saveOrUpdate(a);
		userService.saveOrUpdate(u);
		return "redirect:/";
	}

	// TODO 27.1
	@RequestMapping(value = "/detailUser")
	public String detailHotel(Model model) {
		User u = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
		List<Hotel> hotels = hotelService.getHotelsByUser(u);
		List<Review> reviews = reviewService.getReviewsByUser(u);
		model.addAttribute("hotels", hotels);
		model.addAttribute("reviews", reviews);
		model.addAttribute("user", u);
		model.addAttribute("address", u.getAddress());
		model.addAttribute("district", u.getAddress().getDistrict());
		return "userDetail";
	}

	private List<Role> getAllWithoutAdmin() {
		List<Role> roleList = roleService.getAll();
		List<Role> roleListSelect = new ArrayList<Role>();
		boolean firstUser = true;
		boolean firstHotel = true;
		for (Role role : roleList) {
			if (role.getRoleId() != 1) {
				if (role.getDescription().equals("ROLE_USER") && firstUser) {
					role.setDescription("N�v�t�vn�k");
					firstUser = false;
					roleListSelect.add(role);
				}
				if (role.getDescription().equals("ROLE_HOTELIER") && firstHotel) {
					firstHotel = false;
					role.setDescription("Hoteli�r");
					roleListSelect.add(role);
				}

			}
		}
		return roleListSelect;
	}

}