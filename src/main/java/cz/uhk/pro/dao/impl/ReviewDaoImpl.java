package cz.uhk.pro.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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
	
	@Override
	public List<Double> getAverageReview(Hotel hotel){
			Criteria criteria = currentSession().createCriteria(Review.class);
			
			List<Double> review = new ArrayList<Double>();
			double average = 0.0;
			
			try{
			average = 0.0;
			criteria.setProjection(Projections.avg("reviewAccommodation")).add(Restrictions.eq("hotel",hotel));
			System.out.println(criteria.uniqueResult());
			average = (Double) criteria.uniqueResult();
			System.out.println(average);
	        review.add(average);
			
	        average = 0.0;
			criteria.setProjection(Projections.avg("reviewComplete")).add(Restrictions.eq("hotel",hotel));
			average = (Double) criteria.uniqueResult();
	        review.add(average);
			
	        average = 0.0;
			criteria.setProjection(Projections.avg("reviewEnviroment")).add(Restrictions.eq("hotel",hotel));
			average = (Double) criteria.uniqueResult();
	        review.add(average);
			
	        average = 0.0;
			criteria.setProjection(Projections.avg("reviewFood")).add(Restrictions.eq("hotel",hotel));
			average = (Double) criteria.uniqueResult();
	        review.add(average);
	        
	        average = 0.0;
			criteria.setProjection(Projections.avg("reviewPrice")).add(Restrictions.eq("hotel",hotel));
			average = (Double) criteria.uniqueResult();
	        review.add(average);
			}
			catch(Exception e){
				review.clear();
				review.add(0.0);
				review.add(0.0);
				review.add(0.0);
				review.add(0.0);
				review.add(0.0);
			}
	        return review;
		
	}

}
