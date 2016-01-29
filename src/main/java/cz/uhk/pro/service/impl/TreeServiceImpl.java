package cz.uhk.pro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.pro.dao.GenericDao;
import cz.uhk.pro.dao.TreeDao;
import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Tree;
import cz.uhk.pro.service.TreeService;

@Service
public class TreeServiceImpl extends GenericServiceImpl<Tree, Integer> implements TreeService {

	
	private TreeDao treeDao;
	
	public TreeServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public TreeServiceImpl(@Qualifier("treeDaoImpl") GenericDao<Tree, Integer> genericDao){
		super(genericDao);
		this.treeDao = (TreeDao) genericDao;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Tree> getComForHotel(Hotel hotel){
		return treeDao.getComForHotel(hotel);
	}
}
