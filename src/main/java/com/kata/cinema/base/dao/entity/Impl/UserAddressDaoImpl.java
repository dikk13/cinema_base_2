package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.UserAddressDao;
import com.kata.cinema.base.models.UserAddress;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.kata.cinema.base.dao.util.JpaResultHelper.jpaResultHelper;

@Repository
public class UserAddressDaoImpl extends AbstractDaoImpl<Long, UserAddress> implements UserAddressDao{
    @Override
    public Optional<UserAddress> getUserAddressByUserId(Long userId) {
        return jpaResultHelper(entityManager.createQuery("select ua from UserAddress ua " +
                        "where ua.user.id = :userId", UserAddress.class)
                .setParameter("userId", userId));
    }
}
