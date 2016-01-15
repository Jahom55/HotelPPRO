package cz.uhk.pro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cz.uhk.pro.dao.GenericDao;
import cz.uhk.pro.dao.HotelDao;
import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.service.HotelService;

@Service
public class HotelServiceImpl extends GenericServiceImpl<Hotel, Integer> implements HotelService{
	
	private HotelDao hotelDao;
	
	public HotelServiceImpl(){
		
	}
	
	@Autowired
	public HotelServiceImpl(@Qualifier("hotelDaoImpl") GenericDao<Hotel, Integer> genericDao){
		super(genericDao);
		this.hotelDao = (HotelDao) genericDao;
		
	}

}
