package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AddressDao;
import com.kata.cinema.base.dto.request.AddressRequestDto;
import com.kata.cinema.base.mappers.AddressMapper;
import com.kata.cinema.base.models.Address;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@Transactional
public class AddressServiceImpl extends AbstractServiceImpl<Long, Address> implements AddressService {

    private  final AddressDao addressDao;
    private final AddressMapper addressMapper;

    @Autowired
    protected AddressServiceImpl(AddressDao addressDao, AddressMapper addressMapper) {
        super(addressDao);
        this.addressDao = addressDao;
        this.addressMapper = addressMapper;
    }

    @Override
    public Optional<Address> getAddressByUserId(Long userId) {
        return addressDao.getAddressByUserId(userId);
    }

    @Override
    @Transactional
    public void updateById(Long id, AddressRequestDto addressRequestDto) {
        Optional<Address> optionalAddress = getById(id);
        if (optionalAddress.isPresent()) {
            Address addressToUpdate = addressMapper.toAddress(addressRequestDto);
            addressDao.update(addressToUpdate);
        }
    }
}
