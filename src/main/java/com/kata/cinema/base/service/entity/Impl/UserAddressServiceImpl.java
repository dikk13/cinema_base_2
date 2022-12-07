package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.UserAddressDao;
import com.kata.cinema.base.models.UserAddress;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserAddressServiceImpl extends AbstractServiceImpl<Long, UserAddress> implements UserAddressService {

    private  final UserAddressDao userAddressDao;

    @Autowired
    protected UserAddressServiceImpl(UserAddressDao userAddressDao) {
        super(userAddressDao);
        this.userAddressDao = userAddressDao;
    }

    @Override
    public Optional<UserAddress> getUserAddressByUserId(Long userId) {
        return userAddressDao.getUserAddressByUserId(userId);
    }
}
