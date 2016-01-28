package cz.uhk.pro.controllers;

import java.util.List;

import javax.swing.text.html.parser.Entity;
import javax.xml.ws.Response;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Image;
import cz.uhk.pro.model.Review;
import cz.uhk.pro.model.User;
import cz.uhk.pro.service.HotelService;
import cz.uhk.pro.service.ImageService;
import cz.uhk.pro.service.ReviewService;
import cz.uhk.pro.service.UserService;

@RestController
public class AjaxController {

	
	@Autowired
	ImageService imageService;
	
	@Autowired
	HotelService hotelService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ReviewService reviewService;
	
	@RequestMapping(value="/getImages", method=RequestMethod.GET)
	public ResponseEntity<List<Image>> getAllImages(){
		List<Image> images = imageService.getAll();
		if (images == null){
			return new ResponseEntity<List<Image>>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<List<Image>>(images, HttpStatus.OK);
		}
	}
	@RequestMapping(value="/deleteEmptyHotel", params = "id", method=RequestMethod.DELETE)
	public ResponseEntity<Hotel> deleteHotel(@RequestParam int id){
		Hotel hotel = hotelService.get(id);

		if (hotel == null)
			return new ResponseEntity<Hotel>(HttpStatus.NOT_FOUND);
		
			
			hotelService.remove(hotel);
			return new ResponseEntity<Hotel>(HttpStatus.NO_CONTENT);
		
	}
	
	@RequestMapping(value="/deleteEmptyUser", params = "id", method=RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@RequestParam int id){
		User user = userService.get(id);

		if (user == null)
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		
			
			userService.remove(user);
			return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
		
	}
	
	@RequestMapping(value="/updateReview", method=RequestMethod.POST)
	public ResponseEntity<Byte> updateReview(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "idUser", required = true) int idUser,
			@RequestParam(value = "idHotel", required = true) int idHotel,
			@RequestParam(value = "value", required = true) int value,
			UriComponentsBuilder ucBuilder){
		
		System.out.println(name.toString());
		System.out.println(idUser);
		System.out.println(value);
		Hotel hotel;
		User user;
		Review review;
		try{
			hotel = hotelService.get(idHotel);
			user = userService.get(idUser);
		}catch(Exception e){
			return new ResponseEntity<Byte>(HttpStatus.EXPECTATION_FAILED);
		}
		
		review = reviewService.getReviewsByHotelAndUser(hotel, user);
		if (review == null){
			review = new Review();
			review.setHotel(hotel);
			review.setUser(user);
		}
		//prasarna jak svin
		//accommodation  13
		//complete   8
		//enviroment  10
		//food   4
		//price  5
		byte res = 0 ;
		switch(name.length()){
		case 13:
			review.setReviewAccommodation((byte)value);
			List<Double> d = reviewService.getAverageReview(hotel);
			res = d.get(0).byteValue();
			break;
		case 8:
			review.setReviewComplete((byte)value);
			List<Double> d1 = reviewService.getAverageReview(hotel);
			res = d1.get(1).byteValue();
			break;
		case 10:
			review.setReviewEnviroment((byte)value);
			List<Double> d2 = reviewService.getAverageReview(hotel);
			res = d2.get(2).byteValue();
			break;
		case 4:
			review.setReviewFood((byte)value);
			List<Double> d3 = reviewService.getAverageReview(hotel);
			res = d3.get(3).byteValue();
			break;
		case 5:
			review.setReviewPrice((byte)value);
			List<Double> d4 = reviewService.getAverageReview(hotel);
			res = d4.get(4).byteValue();
			break;

		}
		
		try{
			reviewService.saveOrUpdate(review);
		}
		catch(Exception e){
			return new ResponseEntity<Byte>(HttpStatus.UNPROCESSABLE_ENTITY);
			
		}
			return new ResponseEntity<Byte>(HttpStatus.NO_CONTENT);
		
	}
	
	@RequestMapping(value="/getImages", params = "id", method=RequestMethod.GET)
	public ResponseEntity<List<Image>> getImage(@RequestParam int id){
		Hotel hotel = hotelService.get(id);
		List<Image> images = imageService.getImages(hotel);
		if (images == null){
			return new ResponseEntity<List<Image>>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<List<Image>>(images, HttpStatus.OK);
		}
	}
	
    @RequestMapping("/greeting")
    public List<Image> greeting() {
    	Hibernate.initialize(imageService.getAll());
    	List<Image> images = imageService.getAll();
        return images;
    }
	
	
}
