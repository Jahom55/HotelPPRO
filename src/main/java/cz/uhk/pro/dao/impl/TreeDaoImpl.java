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
public class TreeDaoImpl extends GenericDaoImpl<Tree, Integer> implements TreeDao{
	@Autowired
	UserService userService;
	
	List<Tree> ffinalTree = new ArrayList<Tree>();
	
	@Override
	public List<Tree> getComForHotel(Hotel hotel){
		ffinalTree.clear();
		Criteria criteria = currentSession().createCriteria(Tree.class);
		criteria.add(Restrictions.eq("hotel",hotel));
		List<Tree> tree = criteria.list();
		System.out.println("fads");
		System.out.println(tree.size());
		
		
		for (Tree t : tree) {
			if(t.isRoot()){
				ffinalTree.add(t);
				System.out.println("fads2");
				System.out.println(ffinalTree.size());
				Tree tre = t;
				tree.remove(t);
				serad(tree, tre);
				break;
			}
		}
		
		List<Tree> finalTree = new ArrayList<Tree>();
		

		finalTree.clear();

		return ffinalTree;
		
	}
	
	
	private void serad(List<Tree> tr, Tree tree){
		
		for (Tree t : tr) {
			if(t.getAncestor() == tree.getTreeId()){
				ffinalTree.add(t);
				System.out.println("fads3");
				System.out.println(ffinalTree.size());
				Tree tre = t;
				tr.remove(t);
				if(tr.size() > 0){
					serad(tr, tre);
				}{
					return;
				}
				
			}
		}
		
		
		return;
	}

}
