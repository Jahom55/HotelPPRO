package cz.uhk.pro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cz.uhk.pro.dao.GenericDao;
import cz.uhk.pro.dao.UserDao;
import cz.uhk.pro.model.User;
import cz.uhk.pro.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements UserService {
	
	private UserDao userDao;
	
	public UserServiceImpl(){
		
	}
	
	@Autowired
	public UserServiceImpl(@Qualifier("userDaoImpl") GenericDao<User, Integer> genericDao){
		super(genericDao);
		this.userDao = (UserDao) genericDao;
	}

}
