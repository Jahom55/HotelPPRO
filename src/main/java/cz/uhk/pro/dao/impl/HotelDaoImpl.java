package cz.uhk.pro.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cz.uhk.pro.dao.HotelDao;
import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Review;
import cz.uhk.pro.model.User;

@Repository
public class HotelDaoImpl extends GenericDaoImpl<Hotel, Integer> implements HotelDao {

	
	//Spocita prumerne hodnoceni k jednotlivemu hotelu
	@Override
	public double getRate(Hotel hotel) {
		Criteria criteria = currentSession().createCriteria(Review.class)
                .setProjection(Projections.avg("reviewComplete")).add(Restrictions.eq("hotel",hotel));
        double avarage = (Double) criteria.uniqueResult();
        return avarage;
	}
	
	@Override
	public List<Hotel> getPage(int page, int size){
		Criteria criteria = currentSession().createCriteria(Hotel.class);
		criteria.setFirstResult((page - 1) * size);
		criteria.setMaxResults(size);
		List<Hotel> hotels = criteria.list();
		return hotels;
		
	}
	
	@Override
	public Long countHotels(){
		Criteria criteria = currentSession().createCriteria(Hotel.class);
		criteria.setProjection(Projections.rowCount());
		Long count = (Long) criteria.uniqueResult();
		return count;
		
	}
	
	@Override
	public List<Hotel> getHotelsByUser(User user) {		
		Criteria criteria = currentSession().createCriteria(Hotel.class)
                .add(Restrictions.eq("user",user));
        
        return criteria.list();
	}
	
}
