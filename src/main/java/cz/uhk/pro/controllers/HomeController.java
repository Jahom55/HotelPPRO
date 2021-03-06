package cz.uhk.pro.controllers;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.id.GUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.util.HtmlUtils;

import cz.uhk.pro.model.Address;
import cz.uhk.pro.model.District;
import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.help.HotelUpload;
import cz.uhk.pro.model.Image;
import cz.uhk.pro.model.Person;
import cz.uhk.pro.model.Review;
import cz.uhk.pro.model.Tree;
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
import cz.uhk.pro.service.TreeService;
import cz.uhk.pro.service.TypeService;
import cz.uhk.pro.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired(required = true)
	private AddressService addressService;

	@Autowired(required = true)
	private TypeService typeService;

	@Autowired(required = true)
	private DistrictService districtService;

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
	@Transactional
	public String home(Locale locale, Model model) {
		List<Hotel> hotelList = hotelService.getBest3Hotels();
		/* Inicialization images 
		for (Hotel h : hotelList) {
			Hibernate.initialize(h.getImages());
		}
		*/
		model.addAttribute("hotels", hotelList);
		return "home";
	}

	@RequestMapping(value = "/hotels", method = RequestMethod.POST)
	public String filterHotel(@ModelAttribute("hotel") Hotel hotel, Model model) {
		return "redirect:/hotels/?page=1&size=20&districtId=" + hotel.getAddress().getDistrict().getDistrictId()
				+ "&stars=" + hotel.getStars();
	}

	@RequestMapping(value = "/detailHotelSearch", method = RequestMethod.POST)
	public String searchHotel(@ModelAttribute("hotel") Hotel hotel, Model model) {
		Hotel h = hotelService.getHotelByName(hotel.getName());
		return "redirect:/detail?id=" + h.getHotelId();
	}

	@RequestMapping(value = "/hotels", params = { "page", "size", "districtId", "stars" })
	public String allHotels(Locale locale, Model model, @RequestParam int page, @RequestParam int size,
			@RequestParam int districtId, @RequestParam int stars) {
		List<Hotel> hotelList = hotelService.getPage(page, size, districtService.get(districtId), stars);
		model.addAttribute("hotels", hotelList);
		double number = hotelService.countHotels();
		int pages = (int) Math.ceil(number / (double) size);
		model.addAttribute("pages", pages);
		int from = 1, to = pages;
		if (pages <= 5) {
			from = 1;
			to = pages;
		} else {
			if (page + 2 > pages) {
				from = pages - 4;
				to = pages;
			} else {
				if (page > 2) {
					from = page - 2;
					to = page + 2;
				} else {
					from = 1;
					to = 5;
				}
			}
		}
		List<String> paginator = new ArrayList<String>();

		for (int i = from; i <= to; i++) {
			paginator.add(String.valueOf(i));
		}
		model.addAttribute("paginator", paginator);
		List<District> districtList = districtService.getAll();
		model.addAttribute("districts", districtList);
		Hotel h = new Hotel();
		h.setStars((byte) stars);
		Address a = new Address();
		a.setDistrict(districtService.get(districtId));
		h.setAddress(a);
		if (districtService.get(districtId) != null)
			model.addAttribute("district", h.getAddress().getDistrict());
		model.addAttribute("hotel", h);
		return "hotels";
	}

	@ModelAttribute("allHotels")
	public List<Hotel> populateHotels() {
		return this.hotelService.getPage(2, 1, null, 0);
	}

	@RequestMapping(value = "/hotel")
	public String addHotel(Model model) {
		List<Type> typesList = typeService.getAll();
		model.addAttribute("types", typesList);
		List<District> districtList = districtService.getAll();
		model.addAttribute("districts", districtList);
		Hotel hotel = new Hotel();
		hotel.setName("dummy");
		hotel.setWebsite("dummy");
		hotel.setStars((byte) 3);
		int id = (int) hotelService.add(hotel);
		Hotel h = new Hotel();
		h.setHotelId(id);
		model.addAttribute("hotel", h);
		return "hotelAddEdit";
	}

	@RequestMapping(value = "/hotel", params = "id")
	public String editHotel(Model model, @RequestParam int id) {
		List<Type> typesList = typeService.getAll();
		model.addAttribute("types", typesList);
		List<District> districtList = districtService.getAll();
		model.addAttribute("districts", districtList);
		Hotel h = hotelService.get(id);
		model.addAttribute("hotel", h);
		List<Image> images = imageService.getImages(h);
		model.addAttribute("images", images);
		return "hotelAddEdit";
	}

	@RequestMapping(value = "/detail", params = "id")
	public String detailHotel(Model model, @RequestParam int id, HttpServletRequest request) {
		// User u = userService.findByUserName("admin");
		Hotel h = hotelService.get(id);
		List<Image> images = imageService.getImages(h);
		model.addAttribute("images", images);
		List<Double> forhotel = reviewService.getAverageReview(h);
		Review rko = new Review();
		rko.setReviewAccommodation(Byte.valueOf(forhotel.get(0).byteValue()));
		rko.setReviewComplete(Byte.valueOf(forhotel.get(1).byteValue()));
		rko.setReviewEnviroment(Byte.valueOf(forhotel.get(2).byteValue()));
		rko.setReviewFood(Byte.valueOf(forhotel.get(3).byteValue()));
		rko.setReviewPrice(Byte.valueOf(forhotel.get(4).byteValue()));
		model.addAttribute("type", h.getType());
		model.addAttribute("reviews", rko);
		Review r = new Review();
		if (request.isUserInRole("ROLE_USER")) {
			User user = userService
					.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName().toString());
			if (reviewService.getReviewsByHotelAndUser(h, user) != null)
				r = reviewService.getReviewsByHotelAndUser(h, user);
			model.addAttribute("user", user);
		}

		model.addAttribute("review", r);
		model.addAttribute("hotel", h);
		model.addAttribute("address", h.getAddress());
		model.addAttribute("district", h.getAddress().getDistrict());
		model.addAttribute("equipment", h.getEquipment());

		return "hotelDetail";
	}

	@RequestMapping(value = "/updateHotelsImages", method = RequestMethod.POST)
	public ResponseEntity uploadFile(MultipartHttpServletRequest request) {

		try {
			int id = Integer.valueOf(request.getParameter("id"));
			Iterator<String> itr = request.getFileNames();

			while (itr.hasNext()) {
				String path = "";
				String uploadedFile = itr.next();
				MultipartFile file = request.getFile(uploadedFile);

				String pType = file.getContentType().split("/")[0];
				String sType = file.getContentType().split("/")[1];
				UUID uuid = new UUID(255, 200);
				String name = String.valueOf(uuid.randomUUID());
				String fileName = file.getOriginalFilename();
				if (fileName.length() > 15)
					fileName = fileName.substring(0, 15);
				fileName = String.valueOf(fileName.hashCode());
				path = "D:/sts/work/ProHotel/src/main/webapp/resources/images/" + name + fileName + "." + sType;
				File destination = new File(path);
				file.transferTo(destination);
				String path2 = "";
				path2 = "../../pro/resources/images/" + name + fileName + "." + sType;
				Image image = new Image();
				Hotel hotel = hotelService.get(id);
				image.setHotel(hotel);
				image.setImage(path2);
				File check = new File(path);
				while (!check.exists()) {
					System.out.println("jeste ne");
				}
				imageService.saveOrUpdate(image);
			}
		} catch (Exception e) {
			return new ResponseEntity("{}", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity("{}", HttpStatus.OK);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String searchHotel(Model model) {

		return "";
	}

	@RequestMapping(value = "/updateHotel", method = RequestMethod.POST)
	public String updateHotel(@ModelAttribute("hotel") Hotel hotel, Model model) {
		hotel.setDescription(HtmlUtils.htmlUnescape((hotel.getDescription())));
		Address a = new Address();
		a = hotel.getAddress();
		District d = districtService.get(a.getDistrict().getDistrictId());
		a.setDistrict(d);
		addressService.saveOrUpdate(a);
		equipmentService.saveOrUpdate(hotel.getEquipment());
		hotel.setUser(userService
				.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName().toString()));
		List<Image> imageList = imageService.getImages(hotel);
		hotel.setImage(imageList.get(0).getImage());
		hotelService.saveOrUpdate(hotel);
		return "redirect:/detail/?id=" + hotel.getHotelId();
	}

	@RequestMapping(value = "/remove")
	public String removeHotel(@RequestParam int id, @ModelAttribute Hotel hotel) {
		Hotel p = hotelService.get(id);
		hotelService.remove(p);
		return "redirect:/";
	}

}
