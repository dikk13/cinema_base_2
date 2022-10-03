package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.UserDao;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.service.abstracts.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends AbstractServiceImpl<Long, User> implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        super(userDao);
        this.userDao = userDao;
    }
    @Override
    public Optional<User> getByEmail(String email) {
        return userDao.getByEmail(email);
    }
}
