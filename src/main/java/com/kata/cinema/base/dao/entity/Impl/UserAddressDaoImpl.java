package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.UserAddressDao;
import com.kata.cinema.base.models.UserAddress;
import org.springframework.stereotype.Repository;

@Repository
public class UserAddressDaoImpl extends AbstractDaoImpl<Long, UserAddress> implements UserAddressDao{
}
