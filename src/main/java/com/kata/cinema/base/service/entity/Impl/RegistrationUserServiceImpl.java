package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.RoleDao;
import com.kata.cinema.base.dao.entity.UserDao;
import com.kata.cinema.base.models.FolderMovie;
import com.kata.cinema.base.models.FolderPerson;
import com.kata.cinema.base.models.Role;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.models.enums.Category;
import com.kata.cinema.base.models.enums.Privacy;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.RegistrationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RegistrationUserServiceImpl extends AbstractServiceImpl<Long, User> implements RegistrationUserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private BCryptPasswordEncoder cryptPasswordEncoder;

    @Autowired
    protected RegistrationUserServiceImpl(UserDao userDao, RoleDao roleDao) {
        super(userDao);
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public void register(User user) {
        Optional<Role> roleUser = roleDao.getByName("USER");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleUser.orElse(null));
    }

    private void creatFolderMovieAndFolderPerson(User user) {
        Set<Role> userRoles = new HashSet<>();
        for (Category category : Category.values()) {
            if (!category.equals(Category.CUSTOM)) {
                FolderMovie folderMovie = new FolderMovie();
                folderMovie.setCategory(category);
                folderMovie.setPrivacy(Privacy.PUBLIC);
                folderMovie.setName(category.name());
                folderMovie.setUser(user);
            }
        }
        FolderPerson folderPerson = new FolderPerson();
        folderPerson.setName("Избранные");
        folderPerson.setFavourites(true);
        folderPerson.setUser(user);
        user.setPassword(cryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(userRoles);
        userDao.create(user);
    }
}
