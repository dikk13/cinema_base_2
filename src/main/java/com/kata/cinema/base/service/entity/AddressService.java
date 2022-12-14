package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.dto.request.AddressRequestDto;
import com.kata.cinema.base.models.Address;

public interface AddressService extends AbstractService <Long, Address>{

    void updateById(Long id, AddressRequestDto addressRequestDto);
}
