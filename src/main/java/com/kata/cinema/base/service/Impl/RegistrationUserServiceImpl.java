package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.AbstractDao;
import com.kata.cinema.base.dao.abstracts.RegistrationUserDao;
import com.kata.cinema.base.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationUserServiceImpl extends AbstractServiceImpl<Long, User> implements RegistrationUserDao {

    private final RegistrationUserDao registrationUserDao;

    @Autowired
    protected RegistrationUserServiceImpl(RegistrationUserDao registrationUserDao) {
        super(registrationUserDao);
        this.registrationUserDao = registrationUserDao;
    }

    @Override
    public void register(User user) {

    }
}
