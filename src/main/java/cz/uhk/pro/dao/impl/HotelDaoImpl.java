package cz.uhk.pro.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cz.uhk.pro.dao.HotelDao;
import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Review;

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
	
	
}
