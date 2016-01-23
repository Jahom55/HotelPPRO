package cz.uhk.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.pro.dao.GenericDao;
import cz.uhk.pro.dao.ReviewDao;
import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Review;
import cz.uhk.pro.service.ReviewService;

@Service
public class ReviewServiceImpl extends GenericServiceImpl<Review, Integer> implements ReviewService {

	private ReviewDao reviewDao;
	
	public ReviewServiceImpl(){
		
	}
	
	@Autowired
	public ReviewServiceImpl(@Qualifier("reviewDaoImpl") GenericDao<Review, Integer> genericDao){
		super(genericDao);
		this.reviewDao = (ReviewDao) genericDao;
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Review> getReviewsByHotel(Hotel hotel) {
		return reviewDao.getReviewsByHotel(hotel);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)	
	public List<Double> getAverageReview(Hotel hotel){
		return reviewDao.getAverageReview(hotel);
	}
}
