package cz.uhk.pro.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cz.uhk.pro.dao.TreeDao;
import cz.uhk.pro.model.Address;
import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Tree;
import cz.uhk.pro.model.User;
import cz.uhk.pro.service.AddressService;
import cz.uhk.pro.service.UserService;

@Repository
public class TreeDaoImpl extends GenericDaoImpl<Tree, Integer> implements TreeDao {
	@Autowired
	UserService userService;

	List<Tree> ffinalTree = new ArrayList<Tree>();

	@Override
	public List<Tree> getComForHotel(Hotel hotel) {
		ffinalTree.clear();
		Criteria criteria = currentSession().createCriteria(Tree.class);
		criteria.add(Restrictions.eq("hotel", hotel));
		List<Tree> tree = criteria.list();

		for (Tree t : tree) {
			if (t.isRoot()) {
				ffinalTree.add(t);
				Tree tre = t;
				tree.remove(t);
				srovnej(tree, tre);
				break;
			}
		}

		return ffinalTree;

	}

	private void srovnej(List<Tree> tre, Tree tr) {
		for (Tree t : tre) {
			if (t.getAncestor() == tr.getTreeId()) {
				ffinalTree.add(t);
				if (tre.size() > 0) {
					srovnej(tre, t);
				}
			}
		}
	}

}
