package cz.uhk.pro.dao;

import java.util.List;

import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.User;

public interface HotelDao extends GenericDao<Hotel, Integer> {
	public double getRate(Hotel hotel);
	public List<Hotel> getPage(int page, int size);
	public Long countHotels();
	public List<Hotel> getHotelsByUser(User user);
	
}
