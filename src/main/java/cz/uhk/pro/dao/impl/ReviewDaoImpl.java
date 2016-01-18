package cz.uhk.pro.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cz.uhk.pro.dao.ReviewDao;
import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Review;

@Repository
public class ReviewDaoImpl extends GenericDaoImpl<Review, Integer> implements ReviewDao {

	@Override
	public List<Review> getReviewsByHotel(Hotel hotel) {		
			Criteria criteria = currentSession().createCriteria(Review.class)
	                .add(Restrictions.eq("hotel",hotel));
	        
	        return criteria.list();
		
	}

}
