package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.RoleDao;
import com.kata.cinema.base.models.Role;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RoleDaoImpl extends AbstractDaoImpl<Long, Role> implements RoleDao {

    @Override
    public Optional<Role> getByName(String role) {
        try {
            return Optional.of(entityManager.createQuery("SELECT u FROM Role u WHERE u.role=:role", Role.class)
                    .setParameter("role", role).getSingleResult());
        } catch (Exception e) {
            throw new UsernameNotFoundException("There is no such role");
        }
    }
}
