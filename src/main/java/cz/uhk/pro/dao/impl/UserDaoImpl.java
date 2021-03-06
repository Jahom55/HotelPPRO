package cz.uhk.pro.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cz.uhk.pro.dao.UserDao;
import cz.uhk.pro.model.Review;
import cz.uhk.pro.model.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {

	@Override
	public User findByUserName(String username) {
		Criteria criteria = currentSession().createCriteria(User.class).add(Restrictions.eq("login", username));
		return (User) criteria.uniqueResult();
	}

	@Override
	public List<User> getAll() {
		return currentSession().createCriteria(User.class).addOrder(Order.desc("enabled")).list();
	}

}
