package cz.uhk.pro.dao.impl;

import org.springframework.stereotype.Repository;

import cz.uhk.pro.dao.UserDao;
import cz.uhk.pro.model.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {

}
