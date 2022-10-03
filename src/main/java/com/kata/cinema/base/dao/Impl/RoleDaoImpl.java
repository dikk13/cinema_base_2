package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.RoleDao;
import com.kata.cinema.base.models.Role;
import com.kata.cinema.base.models.User;

import java.util.Optional;

public class RoleDaoImpl extends AbstractDaoImpl<Long, Role> implements RoleDao {

    @Override
    public Optional<Role> getByName(String role) {
        return Optional.of(entityManager.createQuery("SELECT u FROM Role u WHERE u.role=:role", Role.class)
                .setParameter("role", role).getSingleResult());
    }
}
