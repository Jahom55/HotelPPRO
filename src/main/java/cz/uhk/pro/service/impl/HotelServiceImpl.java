package cz.uhk.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.pro.dao.GenericDao;
import cz.uhk.pro.dao.HotelDao;
import cz.uhk.pro.model.District;
import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.User;
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

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public double getRate(Hotel hotel) {
		return hotelDao.getRate(hotel);
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Hotel> getPage(int page, int size, District districtId, int stars){
		return hotelDao.getPage(page, size, districtId, stars);
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Long countHotels(){
		return hotelDao.countHotels();
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Hotel> getHotelsByUser(User user){
		return hotelDao.getHotelsByUser(user);
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Hotel> getBest3Hotels(){
		return hotelDao.getBest3Hotels();
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Hotel getHotelByName(String name){
		return hotelDao.getHotelByName(name);
	}
	

}
