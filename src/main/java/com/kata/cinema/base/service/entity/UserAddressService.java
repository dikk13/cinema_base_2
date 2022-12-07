package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.UserAddress;

import java.util.Optional;

public interface UserAddressService extends AbstractService <Long, UserAddress>{

    Optional<UserAddress> getUserAddressByUserId(Long id);
}
