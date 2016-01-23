package cz.uhk.pro.service;

import java.util.List;

import cz.uhk.pro.model.Hotel;
import cz.uhk.pro.model.Image;

public interface ImageService extends GenericService<Image, Integer> {
	public List<Image> getImages(Hotel hotel);
}
