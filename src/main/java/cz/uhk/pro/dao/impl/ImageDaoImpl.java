package cz.uhk.pro.dao.impl;

import org.springframework.stereotype.Repository;

import cz.uhk.pro.dao.ImageDao;
import cz.uhk.pro.model.Image;

@Repository
public class ImageDaoImpl extends GenericDaoImpl<Image, Integer> implements ImageDao {

}
