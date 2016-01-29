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
import cz.uhk.pro.model.Tree;
import cz.uhk.pro.model.User;
import cz.uhk.pro.service.HotelService;
import cz.uhk.pro.service.ImageService;
import cz.uhk.pro.service.ReviewService;
import cz.uhk.pro.service.TreeService;
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
	
	@Autowired
	TreeService treeService;
	
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
	public ResponseEntity<Review> updateReview(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "idUser", required = true) int idUser,
			@RequestParam(value = "idHotel", required = true) int idHotel,
			@RequestParam(value = "value", required = true) int value,
			UriComponentsBuilder ucBuilder){
		
		Hotel hotel;
		User user;
		Review review;
		try{
			hotel = hotelService.get(idHotel);
			user = userService.get(idUser);
		}catch(Exception e){
			return new ResponseEntity<Review>(HttpStatus.EXPECTATION_FAILED);
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
		
		
		switch(name.length()){
		case 13:
			review.setReviewAccommodation((byte)value);
			break;
		case 8:
			review.setReviewComplete((byte)value);
			break;
		case 10:
			review.setReviewEnviroment((byte)value);
			break;
		case 4:
			review.setReviewFood((byte)value);
			break;
		case 5:
			review.setReviewPrice((byte)value);
			break;

		}
		
		try{
			reviewService.saveOrUpdate(review);
		}
		catch(Exception e){
			return new ResponseEntity<Review>(HttpStatus.UNPROCESSABLE_ENTITY);
			
		}
			return new ResponseEntity<Review>(HttpStatus.NO_CONTENT);

	}
	@RequestMapping(value="/updateReviewComplete", params = "id", method=RequestMethod.GET)
	public ResponseEntity<Review> updateReviewComplete(@RequestParam int id){
		Hotel hotel = hotelService.get(id);
		List<Double> ld = reviewService.getAverageReview(hotel);
		Review review = new Review();
		try{
		review.setReviewAccommodation(ld.get(0).byteValue());
		review.setReviewComplete(ld.get(1).byteValue());
		review.setReviewEnviroment(ld.get(2).byteValue());
		review.setReviewFood(ld.get(3).byteValue());
		review.setReviewPrice(ld.get(4).byteValue());
		
			return new ResponseEntity<Review>(review, HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
		}
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
	
	@RequestMapping(value="/getCom", params = "id", method=RequestMethod.GET)
	public ResponseEntity<List<Tree>> getCom(@RequestParam int id){
		Hotel hotel = hotelService.get(id);
		List<Tree> tree = treeService.getComForHotel(hotel);
		System.out.println("velikost " + tree.size());
		if (tree == null){
			return new ResponseEntity<List<Tree>>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<List<Tree>>(tree, HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value="/createComment", method=RequestMethod.POST)
	public ResponseEntity<Tree> createComment(@RequestParam(value = "body", required = true) String body,
			@RequestParam(value = "idUser", required = true) int idUser,
			@RequestParam(value = "idHotel", required = true) int idHotel,
			@RequestParam(value = "ancestor", required = true) int ancestor,
			@RequestParam(value = "depth", required = true) int depth,
			UriComponentsBuilder ucBuilder){
		Hotel hotel = hotelService.get(idHotel);
		Tree tree = null;
		try{
			List<Tree> t = treeService.getComForHotel(hotel);
			if (t.isEmpty() || t == null){
				Tree tr = new Tree();
				tr.setRoot(true);
				tr.setHotel(hotel);
				tr.setAncestor(0);
				int id = treeService.add(tr);
				tree = treeService.get(id);
			}else{
				for (Tree tre : t) {
					if(tre.isRoot()){
						tree = tre;
						break;
					}
				}
			}
		}
		catch(Exception e){
			Tree tr = new Tree();
			tr.setRoot(true);
			tr.setHotel(hotel);
			tr.setAncestor(0);
			int id = treeService.add(tr);
			tree = treeService.get(id);	
		}
		Tree fTree = new Tree();
		if(ancestor == 0){
			fTree.setAncestor(tree.getTreeId());
		}else{
			fTree.setAncestor(ancestor);
		}
		fTree.setBody(body);
		fTree.setDepth(depth);
		fTree.setHotel(hotel);
		fTree.setRoot(false);
		User user = userService.get(idUser);
		fTree.setUser(user);
		fTree.setDescendant(0);

		try {
			treeService.add(fTree);
		} catch (Exception e) {
			return new ResponseEntity<Tree>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
			return new ResponseEntity<Tree>(HttpStatus.NO_CONTENT);

	}
	
	
}
