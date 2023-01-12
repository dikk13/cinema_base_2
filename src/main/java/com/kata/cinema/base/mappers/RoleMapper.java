package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.response.RoleResponseDto;
import com.kata.cinema.base.models.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(source = "role", target = "name")
    RoleResponseDto toDto(Role role);
}
