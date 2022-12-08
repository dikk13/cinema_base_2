package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.AddressDao;
import com.kata.cinema.base.models.Address;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.kata.cinema.base.dao.util.JpaResultHelper.jpaResultHelper;

@Repository
public class AddressDaoImpl extends AbstractDaoImpl<Long, Address> implements AddressDao {
    @Override
    public Optional<Address> getAddressByUserId(Long userId) {
        return jpaResultHelper(entityManager.createQuery("select ua from Address ua " +
                        "where ua.user.id = :userId", Address.class)
                .setParameter("userId", userId));
    }
}
