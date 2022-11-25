package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dto.response.RoleResponseDto;
import com.kata.cinema.base.dto.response.UserResponseDto;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.service.dto.UserDtoService;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
public class UserDtoServiceImpl extends AbstractServiceImpl<Long, User> implements UserDtoService {

    protected UserDtoServiceImpl(@Qualifier("userDaoImpl") AbstractDao<Long, User> abstractDao) {
        super(abstractDao);
    }

    @Override
    public UserResponseDto getUserResponseDto(User targetUser) {
        if (targetUser != null) {
            return new UserResponseDto(
                    targetUser.getId(),
                    targetUser.getEmail(),
                    targetUser.getFirstName(),
                    targetUser.getLastName(),
                    targetUser.getAvatarUrl(),
                    targetUser.getBirthday(),
                    targetUser.getRole().stream().map(r -> new RoleResponseDto(r.getId(), r.getRole())).collect(Collectors.toList()));
        }
        return null;
    }
}
