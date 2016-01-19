package cz.uhk.pro.service;

import java.util.List;

import cz.uhk.pro.model.Hotel;

public interface HotelService extends GenericService<Hotel, Integer> {
	public double getRate(Hotel hotel);
	public List<Hotel> getPage(int page, int size);
	public Long countHotels();

}
