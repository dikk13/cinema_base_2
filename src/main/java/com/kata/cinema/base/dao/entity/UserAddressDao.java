package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.UserAddress;

import java.util.Optional;

public interface UserAddressDao extends AbstractDao<Long, UserAddress>{

    Optional<UserAddress> getUserAddressByUserId(Long userId);
}
