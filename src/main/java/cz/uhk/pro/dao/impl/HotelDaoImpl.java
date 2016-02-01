package cz.uhk.pro.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cz.uhk.pro.dao.HotelDao;
import cz.uhk.pro.model.District;
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
	public List<Hotel> getPage(int page, int size, District districtID, int stars){
		Criteria criteria = currentSession().createCriteria(Hotel.class);
		if(districtID != null){
			criteria.createAlias("address", "a");
			criteria.add(Restrictions.eq("a.district", districtID ));
		}
		if(stars != 0) criteria.add(Restrictions.eq("stars",(byte) stars));
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
	
	@Override
	public List<Hotel> getBest3Hotels() {		
		Criteria criteria = currentSession().createCriteria(Hotel.class).addOrder(Order.desc("counter"));
		criteria.setMaxResults(3);        
        return criteria.list();
	}
	
	@Override
	public Hotel getHotelByName(String name){
		Criteria criteria = currentSession().createCriteria(Hotel.class).add(Restrictions.eq("name", name));
		return (Hotel) criteria.uniqueResult(); 
		
	}
	
}
