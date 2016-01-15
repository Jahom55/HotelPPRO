package cz.uhk.pro.dao.impl;

import org.springframework.stereotype.Repository;

import cz.uhk.pro.dao.ReviewDao;
import cz.uhk.pro.model.Review;

@Repository
public class ReviewDaoImpl extends GenericDaoImpl<Review, Integer> implements ReviewDao {

}
