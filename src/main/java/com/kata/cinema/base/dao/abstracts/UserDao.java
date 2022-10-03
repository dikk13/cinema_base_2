package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.models.User;

import java.util.Optional;

public interface UserDao extends AbstractDao<Long, User> {

    Optional<User> getByEmail(String email);
}
