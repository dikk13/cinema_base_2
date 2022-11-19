package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.RoleDao;
import com.kata.cinema.base.models.Role;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.kata.cinema.base.dao.util.JpaResultHelper.jpaResultHelper;

@Repository
public class RoleDaoImpl extends AbstractDaoImpl<Long, Role> implements RoleDao {

    @Override
    public Optional<Role> getByName(String role) {
        try {
            return jpaResultHelper(entityManager.createQuery("SELECT u FROM Role u WHERE u.role=:role", Role.class)
                    .setParameter("role", role));
        } catch (Exception e) {
            throw new UsernameNotFoundException("There is no such role");
        }
    }
}
