package cz.uhk.pro.dao.impl;

import org.springframework.stereotype.Repository;

import cz.uhk.pro.dao.AddressDao;
import cz.uhk.pro.model.Address;

@Repository
public class AddressDaoImpl extends GenericDaoImpl<Address, Integer> implements AddressDao{

}
