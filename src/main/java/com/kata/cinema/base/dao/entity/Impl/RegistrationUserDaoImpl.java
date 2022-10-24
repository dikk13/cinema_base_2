package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.RegistrationUserDao;
import com.kata.cinema.base.dao.entity.RoleDao;
import com.kata.cinema.base.dao.entity.UserDao;
import com.kata.cinema.base.models.FolderMovie;
import com.kata.cinema.base.models.FolderPerson;
import com.kata.cinema.base.models.Role;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.models.enums.Category;
import com.kata.cinema.base.models.enums.Privacy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public class RegistrationUserDaoImpl extends AbstractDaoImpl<Long, User> implements RegistrationUserDao {

    public void register(User user) {

    }
}
