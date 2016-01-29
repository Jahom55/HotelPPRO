package cz.uhk.pro.service;

import java.util.List;

import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Tree;

public interface TreeService extends GenericService<Tree, Integer> {
	public List<Tree> getComForHotel(Hotel hotel);
}
