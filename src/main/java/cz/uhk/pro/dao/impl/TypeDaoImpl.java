package cz.uhk.pro.dao.impl;

import org.springframework.stereotype.Repository;

import cz.uhk.pro.dao.TypeDao;
import cz.uhk.pro.model.Type;

@Repository
public class TypeDaoImpl extends GenericDaoImpl<Type, Integer> implements TypeDao {

}
