package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.User;


public interface RegistrationUserDao extends AbstractDao<Long, User> {

    void register(User user);


}
