package cz.uhk.pro.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cz.uhk.pro.dao.ImageDao;
import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Image;

@Repository
public class ImageDaoImpl extends GenericDaoImpl<Image, Integer> implements ImageDao {
	
	@Override
	public List<Image> getImages(Hotel hotel){
		Criteria criteria = currentSession().createCriteria(Image.class)
				.add(Restrictions.eq("hotel",hotel));
		List<Image> images = criteria.list();
		return images;
	}
}
