package cz.uhk.pro.dao;

import cz.uhk.pro.model.User;

public interface UserDao extends GenericDao<User, Integer>{
	User findByUserName(String username);
}
