package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.models.User;

public interface RegistrationUserService extends AbstractService<Long, User> {

    void register(User user);

}
