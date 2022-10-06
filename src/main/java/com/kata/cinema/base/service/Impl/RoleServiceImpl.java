package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.AbstractDao;
import com.kata.cinema.base.dao.abstracts.RoleDao;
import com.kata.cinema.base.models.Role;
import com.kata.cinema.base.service.abstracts.RoleService;

import java.util.Optional;

public class RoleServiceImpl extends AbstractServiceImpl<Long, Role> implements RoleService {

    private final RoleDao roleDao;

    protected RoleServiceImpl(RoleDao roleDao) {
        super(roleDao);
        this.roleDao = roleDao;
    }

    @Override
    public Optional<Role> getByName(String role) {
        return roleDao.getByName(role);
    }
}
