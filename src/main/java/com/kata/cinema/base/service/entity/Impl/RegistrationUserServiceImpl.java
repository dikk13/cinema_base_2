package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.RegistrationUserDao;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.RegistrationUserService;
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
