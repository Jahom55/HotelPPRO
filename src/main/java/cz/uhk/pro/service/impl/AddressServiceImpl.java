package cz.uhk.pro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cz.uhk.pro.dao.AddressDao;
import cz.uhk.pro.dao.GenericDao;
import cz.uhk.pro.model.Address;
import cz.uhk.pro.service.AddressService;

@Service
public class AddressServiceImpl extends GenericServiceImpl<Address, Integer> implements AddressService {

	private AddressDao addressDao;
	
	public AddressServiceImpl(){
	}
	
	@Autowired
	public AddressServiceImpl(@Qualifier("addressDaoImpl") GenericDao<Address, Integer> genericDao){
		super(genericDao);
		this.addressDao = (AddressDao) genericDao;
		
	}
}
