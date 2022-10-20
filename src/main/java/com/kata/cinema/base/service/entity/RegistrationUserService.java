package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.User;

public interface RegistrationUserService extends AbstractService<Long, User> {

    void register(User user);

}
