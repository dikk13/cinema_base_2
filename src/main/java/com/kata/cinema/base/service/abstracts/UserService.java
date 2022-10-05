package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.models.User;

import java.util.Optional;

public interface UserService extends AbstractService<Long, User>  {

    Optional<User> getByEmail(String email);

}
