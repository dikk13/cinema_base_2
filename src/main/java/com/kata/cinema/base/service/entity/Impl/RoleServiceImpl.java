package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.RoleDao;
import com.kata.cinema.base.models.Role;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
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
