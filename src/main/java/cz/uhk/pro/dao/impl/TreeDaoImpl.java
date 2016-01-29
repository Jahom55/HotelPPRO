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
		
		
		for (Tree t : tree) {
			if(t.isRoot()){
				ffinalTree.add(t);
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
		System.out.println(tr.size());
		for (int i = 0; i < tr.size(); i++) {
			System.out.println(i);
			System.out.println(tr.get(0).getTreeId());
			if(tr.get(i).getAncestor() == tree.getTreeId()){
				System.out.println("tu");
				Tree t = tr.get(i);
				ffinalTree.add(t);
				System.out.println(ffinalTree.size());
				Tree tre = t;
				//List<Tree> tr2 = tr;
				//tr2.remove(t);
				//serad(tr2, tre);
				
				tr.remove(t);
				if(tr.size() > 0){
					serad(tr, tre);
				}{
					break;
				}
				
			}
		}
		
		
		return;
	}

}
