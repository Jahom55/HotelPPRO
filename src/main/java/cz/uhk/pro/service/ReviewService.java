package cz.uhk.pro.service;

import java.util.List;

import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Review;
import cz.uhk.pro.model.User;

public interface ReviewService extends GenericService<Review, Integer> {
	public List<Review> getReviewsByHotel(Hotel hotel);
	public List<Double> getAverageReview(Hotel hotel);
	public Review getReviewsByHotelAndUser(Hotel hotel, User user);
}
