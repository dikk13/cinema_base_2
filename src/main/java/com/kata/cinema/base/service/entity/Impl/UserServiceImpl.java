package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.config.Cryptor;
import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dao.entity.UserDao;
import com.kata.cinema.base.dto.request.PasswordChangeRequestDto;
import com.kata.cinema.base.dto.request.UserRequestDto;
import com.kata.cinema.base.exception.PasswordUncoincidenceException;
import com.kata.cinema.base.exception.UpdateEntityException;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl extends AbstractServiceImpl<Long, User> implements UserService {

    private final UserDao userDao;
    private final Cryptor cryptor;

    @Autowired
    public UserServiceImpl(@Qualifier("userDaoImpl") AbstractDao<Long, User> abstractDao, UserDao userDao, Cryptor cryptor) {
        super(abstractDao);
        this.userDao = userDao;
        this.cryptor = cryptor;
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Transactional
    @Override
    public void changePassword(PasswordChangeRequestDto passwordChangeRequestDto, User targetUser) {
        if (!cryptor.getCryptor().matches(passwordChangeRequestDto.getOldPassword(), targetUser.getPassword())) {
            throw new PasswordUncoincidenceException("Предыдущие пароли не совпадают");
        }
        targetUser.setPassword(cryptor.getCryptor().encode(passwordChangeRequestDto.getNewPassword()));
        passwordChangeRequestDto.setOldPassword("");
        passwordChangeRequestDto.setNewPassword("");
        userDao.update(targetUser);
    }

    @Transactional
    @Override
    public void disableUser(User targetUser) {
        if (targetUser != null) {
            targetUser.setEnabled(Boolean.FALSE);
            userDao.update(targetUser);
        }
    }

    @Transactional
    @Override
    public void changeUserDetails(UserRequestDto userRequestDto, User targetUser) {
        targetUser.setEmail(userRequestDto.getEmail());
        targetUser.setFirstName(userRequestDto.getFirstName());
        targetUser.setLastName(userRequestDto.getLastName());
        targetUser.setBirthday(userRequestDto.getBirthday());
        userDao.update(targetUser);
    }
}
