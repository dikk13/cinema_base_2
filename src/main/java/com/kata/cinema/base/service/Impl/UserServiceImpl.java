package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.config.Cryptor;
import com.kata.cinema.base.dao.abstracts.AbstractDao;
import com.kata.cinema.base.dao.abstracts.UserDao;
import com.kata.cinema.base.dto.request.PasswordChangeRequestDto;
import com.kata.cinema.base.dto.request.UserRequestDto;
import com.kata.cinema.base.exception.PasswordUncoincidenceException;
import com.kata.cinema.base.exception.UpdateEntityException;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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
        if (cryptor.getCryptor().matches(passwordChangeRequestDto.getOldPassword(), targetUser.getPassword())) {
            targetUser.setPassword(cryptor.getCryptor().encode(passwordChangeRequestDto.getNewPassword()));
            passwordChangeRequestDto.setOldPassword("");
            passwordChangeRequestDto.setNewPassword("");
            try {
                userDao.update(targetUser);
            } catch (Exception e) {
                throw new UpdateEntityException("При попытке обновления объекта в БД произошла ошибка");
            }
        } else {
            throw new PasswordUncoincidenceException("Предыдущие пароли не совпадают");
        }
    }

    @Transactional
    @Override
    public void disableUser(User targetUser) {
        if (targetUser != null) {
            targetUser.setEnabled(Boolean.FALSE);
            try {
                userDao.update(targetUser);
            }
            catch (Exception e) {
                throw new UpdateEntityException("При попытке обновления объекта в БД произошла ошибка");
            }
        }
    }

    @Transactional
    @Override
    public void changeUserDetails(UserRequestDto userRequestDto, User targetUser) {
        if (targetUser != null && userRequestDto != null) {
            targetUser.setEmail(userRequestDto.getEmail());
            targetUser.setFirst_name(userRequestDto.getFirstName());
            targetUser.setLast_name(userRequestDto.getLastName());
            targetUser.setBirthday(userRequestDto.getBirthday());
            try {
                userDao.update(targetUser);
            }
            catch (Exception e) {
                throw new UpdateEntityException("При попытке обновления объекта в БД произошла ошибка");
            }
        }
    }
}
