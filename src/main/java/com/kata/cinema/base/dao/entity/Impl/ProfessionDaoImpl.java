package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.ProfessionDao;
import com.kata.cinema.base.models.Profession;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.kata.cinema.base.dao.util.JpaResultHelper.jpaResultHelper;

@Repository
public class ProfessionDaoImpl extends AbstractDaoImpl<Long, Profession> implements ProfessionDao {

    @Override
    public Optional<Profession> getByName(String profession) {
        return jpaResultHelper(entityManager.createQuery("SELECT u FROM Profession u WHERE u.name=:name", Profession.class)
                .setParameter("name", profession));
    }
}
