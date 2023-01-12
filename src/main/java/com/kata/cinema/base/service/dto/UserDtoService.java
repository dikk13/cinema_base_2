package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.dto.request.UserRequestDto;
import com.kata.cinema.base.dto.response.UserResponseDto;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.service.entity.AbstractService;

import javax.management.relation.RoleNotFoundException;

public interface UserDtoService extends AbstractService<Long, User> {
   UserResponseDto getUserResponseDto (User targetUser);

   UserResponseDto getUserResponseDtoById (Long userId);
   void updateUser(Long userId, UserRequestDto userRequestDto);
   void create(UserRequestDto userRequestDto);
   void disableUser(Long userId);
   void addRole(Long userId, Long roleId);
   void deleteRole(Long userId, Long roleId);
}
