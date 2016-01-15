package cz.uhk.pro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cz.uhk.pro.dao.GenericDao;
import cz.uhk.pro.dao.ImageDao;
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

}
