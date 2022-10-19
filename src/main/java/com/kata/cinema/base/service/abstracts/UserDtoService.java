package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.dto.response.UserResponseDto;
import com.kata.cinema.base.models.User;

public interface UserDtoService extends AbstractService <Long, User>{
   UserResponseDto getUserResponseDto (User targetUser);
}
