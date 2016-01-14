package cz.uhk.pro.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cz.uhk.pro.dao.GenericDao;
import cz.uhk.pro.dao.PersonDao;
import cz.uhk.pro.model.Person;
import cz.uhk.pro.service.PersonService;

@Service
public class PersonServiceImpl extends GenericServiceImpl<Person, Integer>
        implements PersonService {

    private PersonDao personDao;
    public PersonServiceImpl(){

    }
    @Autowired
    public PersonServiceImpl(
        @Qualifier("personDaoImpl") GenericDao<Person, Integer> genericDao) {
        super(genericDao);
        this.personDao = (PersonDao) genericDao;
    }
}
