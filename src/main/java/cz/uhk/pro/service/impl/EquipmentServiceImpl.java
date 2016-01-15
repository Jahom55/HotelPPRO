package cz.uhk.pro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cz.uhk.pro.dao.EquipmentDao;
import cz.uhk.pro.dao.GenericDao;
import cz.uhk.pro.model.Equipment;
import cz.uhk.pro.service.EquipmentService;

@Service
public class EquipmentServiceImpl extends GenericServiceImpl<Equipment, Integer> implements EquipmentService {
	
	private EquipmentDao equipmentDao;
	
	public EquipmentServiceImpl(){
		
	}
	
	@Autowired
	public EquipmentServiceImpl(@Qualifier("equipmentDaoImpl") GenericDao<Equipment, Integer> genericDao){
		super(genericDao);
		this.equipmentDao = (EquipmentDao) genericDao;
	}

}
