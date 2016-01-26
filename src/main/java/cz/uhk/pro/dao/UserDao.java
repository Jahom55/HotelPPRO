package cz.uhk.pro.dao;

import java.util.List;

import cz.uhk.pro.model.User;

public interface UserDao extends GenericDao<User, Integer>{
	User findByUserName(String username);
	public List<User> getAll();
}
