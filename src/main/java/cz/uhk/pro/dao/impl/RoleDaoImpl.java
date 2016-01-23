package cz.uhk.pro.dao.impl;

import org.springframework.stereotype.Repository;

import cz.uhk.pro.dao.RoleDao;
import cz.uhk.pro.model.Role;

@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role, Integer> implements RoleDao {

}
