package cz.uhk.pro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cz.uhk.pro.dao.GenericDao;
import cz.uhk.pro.dao.RoleDao;
import cz.uhk.pro.model.Role;
import cz.uhk.pro.service.RoleService;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, Integer> implements RoleService {
	
	private RoleDao roleDao;
	
	public RoleServiceImpl(){
		
	}
	
	@Autowired
	public RoleServiceImpl(@Qualifier("roleDaoImpl") GenericDao<Role, Integer> genericDao){
		super(genericDao);
		this.roleDao = (RoleDao) genericDao;
		
	}

}
