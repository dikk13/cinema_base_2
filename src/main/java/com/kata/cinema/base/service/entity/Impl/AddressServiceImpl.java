package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AddressDao;
import com.kata.cinema.base.dto.request.AddressRequestDto;
import com.kata.cinema.base.exception.AddressIdNotFoundException;
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
    @Transactional
    public void updateById(Long id, AddressRequestDto addressRequestDto) {
        Optional<Address> addressContainer= getById(id);
        if (addressContainer.isPresent()) {
            addressContainer.get().setStreet(addressMapper.toAddress(addressRequestDto).getStreet());
            addressContainer.get().setCity(addressMapper.toAddress(addressRequestDto).getCity());
            addressDao.update(addressContainer.get());
        } else {
            throw new AddressIdNotFoundException("Address with this ID: " + id + " ,don't found ") {};
        }
    }
}
