package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.UserDao;
import com.kata.cinema.base.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<Long, User> implements UserDao {
    @Override
    public Optional<User> getByEmail(String email) {
            return Optional.of(entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.role WHERE u.email=:email", User.class)
                    .setParameter("email", email).getSingleResult());
        }
    }
