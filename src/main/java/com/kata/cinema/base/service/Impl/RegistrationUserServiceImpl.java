package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.AbstractDao;
import com.kata.cinema.base.dao.abstracts.RegistrationUserDao;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.service.abstracts.RegistrationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationUserServiceImpl extends AbstractServiceImpl<Long, User> implements RegistrationUserService {

    @Autowired
    protected RegistrationUserServiceImpl(RegistrationUserDao registrationUserDao) {
        super(registrationUserDao);
    }

    @Override
    public void register(User user) {

    }
}
