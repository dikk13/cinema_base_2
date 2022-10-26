package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.dao.entity.Impl.AbstractDaoImpl;
import com.kata.cinema.base.models.Role;
import com.kata.cinema.base.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public class RegistrationUserDaoImpl extends AbstractDaoImpl<Long, User> implements RegistrationUserDao {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder encoder;

    public RegistrationUserDaoImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.encoder = encoder;
    }

    @Transactional
    public void register(User user) {
        Optional<Role> roleUser = roleDao.getByName("USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser.orElse(null));
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(userRoles);
        userDao.create(user);
    }
}
