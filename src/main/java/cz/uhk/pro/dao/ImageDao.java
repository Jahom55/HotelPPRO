package cz.uhk.pro.dao;

import java.util.List;

import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Image;

public interface ImageDao extends GenericDao<Image, Integer> {
	public List<Image> getImages(Hotel hotel);

}
