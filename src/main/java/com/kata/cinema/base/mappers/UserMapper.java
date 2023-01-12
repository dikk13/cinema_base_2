package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.UserRegistrationRequestDto;
import com.kata.cinema.base.dto.request.UserRequestDto;
import com.kata.cinema.base.dto.response.UserResponseDto;
import com.kata.cinema.base.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {


    UserRegistrationRequestDto toDto(User user);

    User toUser(UserRegistrationRequestDto requestDto);

    UserResponseDto toUserResponseDto(User user);
    User toUserFromUserRequestDto(UserRequestDto userRequestDto);
}
