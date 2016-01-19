package cz.uhk.pro.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.pro.dao.GenericDao;
import cz.uhk.pro.dao.UserDao;
import cz.uhk.pro.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<cz.uhk.pro.model.User, Integer> implements UserService, UserDetailsService {
	
	private UserDao userDao;
	
	public UserServiceImpl(){
		
	}
	
	@Autowired
	public UserServiceImpl(@Qualifier("userDaoImpl") GenericDao<cz.uhk.pro.model.User, Integer> genericDao){
		super(genericDao);
		this.userDao = (UserDao) genericDao;
	}
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public UserDetails loadUserByUsername(String username) 
               throws UsernameNotFoundException {

		cz.uhk.pro.model.User user = userDao.findByUserName(username);
		List<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
		authority.add((GrantedAuthority) user.getRole());

		return buildUserForAuthentication(user, authority);
		

	}

	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(cz.uhk.pro.model.User user, 
		 List<GrantedAuthority> authorities) {
		return new User(user.getLogin(), 
			user.getPassword(), user.isEnabled(), 
                        true, true, true, authorities);
	}
	
	
	
	

}
