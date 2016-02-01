package cz.uhk.pro.dao;

import java.util.List;

import cz.uhk.pro.model.District;
import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.User;

public interface HotelDao extends GenericDao<Hotel, Integer> {
	public double getRate(Hotel hotel);
	public List<Hotel> getPage(int page, int size, District districtID, int stars);
	public Long countHotels();
	public List<Hotel> getHotelsByUser(User user);
	public List<Hotel> getBest3Hotels();
	public Hotel getHotelByName(String name);
	
}
