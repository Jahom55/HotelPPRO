package cz.uhk.pro.dao;

import java.util.List;

import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Review;
import cz.uhk.pro.model.User;

public interface ReviewDao extends GenericDao<Review, Integer> {

	public List<Review> getReviewsByHotel(Hotel hotel);
	public List<Double> getAverageReview(Hotel hotel);
	public Review getReviewsByHotelAndUser(Hotel hotel, User user);
}
