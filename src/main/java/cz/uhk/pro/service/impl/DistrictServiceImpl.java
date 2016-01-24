package cz.uhk.pro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cz.uhk.pro.dao.DistrictDao;
import cz.uhk.pro.dao.GenericDao;
import cz.uhk.pro.model.District;
import cz.uhk.pro.service.DistrictService;

@Service
public class DistrictServiceImpl extends GenericServiceImpl<District, Integer> implements DistrictService {

	private DistrictDao districtDao;
	
	public DistrictServiceImpl(){
		
		
	}
	
	@Autowired
	public DistrictServiceImpl(@Qualifier("districtDaoImpl") GenericDao<District, Integer> genericDao){
		super(genericDao);
		this.districtDao = (DistrictDao) genericDao;
		
	}
}
