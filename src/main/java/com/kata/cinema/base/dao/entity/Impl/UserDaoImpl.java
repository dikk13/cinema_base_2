package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.UserDao;
import com.kata.cinema.base.models.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.kata.cinema.base.dao.util.JpaResultHelper.jpaResultHelper;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<Long, User> implements UserDao {
    @Override
    public Optional<User> getByEmail(String email) {
        try {
            return jpaResultHelper(entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.role WHERE u.email=:email", User.class)
                    .setParameter("email", email));
        } catch (Exception e) {
            throw new UsernameNotFoundException("There is no such email");
        }
    }

   public User getByRole (String roleUser){

           return entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.role r WHERE r.role=:roleUser",User.class)
                   .setParameter("roleUser",roleUser)
                   .getSingleResult();

    }
}

