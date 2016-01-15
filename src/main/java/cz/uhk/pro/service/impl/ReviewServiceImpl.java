package cz.uhk.pro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cz.uhk.pro.dao.GenericDao;
import cz.uhk.pro.dao.ReviewDao;
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
}
