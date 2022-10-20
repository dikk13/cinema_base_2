package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.Role;

import java.util.Optional;

public interface RoleDao extends AbstractDao<Long, Role> {

    Optional<Role> getByName(String role);
}
