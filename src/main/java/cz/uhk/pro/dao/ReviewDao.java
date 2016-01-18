package cz.uhk.pro.dao;

import java.util.List;

import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Review;

public interface ReviewDao extends GenericDao<Review, Integer> {

	public List<Review> getReviewsByHotel(Hotel hotel);
}
