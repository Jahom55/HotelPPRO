package cz.uhk.pro.service;

import java.util.List;

import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Review;

public interface ReviewService extends GenericService<Review, Integer> {
	public List<Review> getReviewsByHotel(Hotel hotel);
}
