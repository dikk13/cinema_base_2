package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.dto.response.UserResponseDto;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.service.entity.AbstractService;

public interface UserDtoService extends AbstractService<Long, User> {
   UserResponseDto getUserResponseDto (User targetUser);
}
