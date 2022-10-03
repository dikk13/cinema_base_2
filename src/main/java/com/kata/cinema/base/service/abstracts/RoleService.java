package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.models.Role;

import java.util.Optional;

public interface RoleService extends AbstractService<Long, Role> {

    Optional<Role> getByName(String role);
}
