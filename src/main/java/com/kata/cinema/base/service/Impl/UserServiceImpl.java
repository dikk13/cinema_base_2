package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.AbstractDao;
import com.kata.cinema.base.dao.abstracts.UserDao;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl extends AbstractServiceImpl<Long, User> implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(@Qualifier("userDaoImpl") AbstractDao<Long, User> abstractDao, UserDao userDao) {
        super(abstractDao);
        this.userDao = userDao;
    }
    @Override
    public Optional<User> getByEmail(String email) {
        return userDao.getByEmail(email);
    }
}
