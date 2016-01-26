package cz.uhk.pro.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import cz.uhk.pro.model.User;

public interface UserService extends GenericService<User, Integer> {
	public User findByUserName(String username);
	public List<User> getAll() ;
}
