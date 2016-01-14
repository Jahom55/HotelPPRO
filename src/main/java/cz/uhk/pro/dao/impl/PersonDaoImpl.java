package cz.uhk.pro.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cz.uhk.pro.dao.PersonDao;
import cz.uhk.pro.model.Person;

@Repository
public class PersonDaoImpl extends GenericDaoImpl<Person, Integer> implements PersonDao {

}