package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.RegistrationUserDao;
import com.kata.cinema.base.dao.abstracts.RoleDao;
import com.kata.cinema.base.dao.abstracts.UserDao;
import com.kata.cinema.base.models.Role;
import com.kata.cinema.base.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

public class RegistrationUserDaoImpl extends AbstractDaoImpl<Long, User> implements RegistrationUserDao {

    private final UserDao userDao;

    private final RoleDao roleDao;

    private final BCryptPasswordEncoder cryptPasswordEncoder;


    public RegistrationUserDaoImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder cryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.cryptPasswordEncoder = cryptPasswordEncoder;
    }

    @Transactional
    public void register(User user) {
        Optional<Role> roleUser = roleDao.getByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser.orElse(null));

        user.setPassword(cryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(userRoles);

        userDao.create(user);



    }
}
