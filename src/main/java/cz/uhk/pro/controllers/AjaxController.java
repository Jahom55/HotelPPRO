package cz.uhk.pro.controllers;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Image;
import cz.uhk.pro.service.HotelService;
import cz.uhk.pro.service.ImageService;

@RestController
public class AjaxController {

	
	@Autowired
	ImageService imageService;
	
	@Autowired
	HotelService hotelService;
	
	@RequestMapping(value="/getImages", method=RequestMethod.GET)
	public ResponseEntity<List<Image>> getAllImages(){
		List<Image> images = imageService.getAll();
		if (images == null){
			return new ResponseEntity<List<Image>>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<List<Image>>(images, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/getImages/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<Image>> getImage(@PathVariable("id") int id){
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
