package cz.uhk.pro.dao;

import java.util.List;

import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Tree;

public interface TreeDao extends GenericDao<Tree, Integer> {
	public List<Tree> getComForHotel(Hotel h);
		
}
