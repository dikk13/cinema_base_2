package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.dto.request.AddressRequestDto;
import com.kata.cinema.base.models.Address;

import java.util.Optional;

public interface AddressService extends AbstractService <Long, Address>{

    Optional<Address> getAddressByUserId(Long id);

    void updateById(Long id, AddressRequestDto addressRequestDto);
}
