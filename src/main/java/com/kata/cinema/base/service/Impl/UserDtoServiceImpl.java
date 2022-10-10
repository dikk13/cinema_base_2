package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.AbstractDao;
import com.kata.cinema.base.dto.response.RoleResponseDto;
import com.kata.cinema.base.dto.response.UserResponseDto;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.service.abstracts.UserDtoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
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
                    targetUser.getFirst_name(),
                    targetUser.getLast_name(),
                    targetUser.getAvatarUrl(),
                    targetUser.getBirthday().toString(),
                    targetUser.getRole().stream().map(r -> new RoleResponseDto(r.getId(), r.getRole())).collect(Collectors.toList()));
        }
        return null;
    }
}
