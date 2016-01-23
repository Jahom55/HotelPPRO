package cz.uhk.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.pro.dao.GenericDao;
import cz.uhk.pro.dao.ImageDao;
import cz.uhk.pro.dao.impl.ImageDaoImpl;
import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Image;
import cz.uhk.pro.service.ImageService;

@Service
public class ImageServiceImpl extends GenericServiceImpl<Image, Integer> implements ImageService {
	
	private ImageDao imageDao;
	
	public ImageServiceImpl(){
		
	}
	
	@Autowired
	public ImageServiceImpl(@Qualifier("imageDaoImpl") GenericDao<Image, Integer> genericDao){
		super(genericDao);
		this.imageDao = (ImageDao) genericDao;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Image> getImages(Hotel hotel){
		return imageDao.getImages(hotel);
		
	}

}
