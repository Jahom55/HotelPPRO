package cz.uhk.pro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cz.uhk.pro.dao.GenericDao;
import cz.uhk.pro.dao.TypeDao;
import cz.uhk.pro.model.Type;
import cz.uhk.pro.service.TypeService;

@Service
public class TypeServiceImpl extends GenericServiceImpl<Type, Integer> implements TypeService {
	
	private TypeDao typeDao;
	
	public TypeServiceImpl(){
		
	}

	@Autowired
	public TypeServiceImpl(@Qualifier("typeDaoImpl") GenericDao<Type, Integer> genericDao){
		super(genericDao);
		this.typeDao = (TypeDao) genericDao;
	}
	
}
