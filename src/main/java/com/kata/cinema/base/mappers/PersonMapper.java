package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.PersonRequestDto;
import com.kata.cinema.base.models.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "height", target = "height")
    @Mapping(source = "birthday", target = "dateOfBirth")
    @Mapping(source = "placeBirthday", target = "placeOfBirth")
    @Mapping(source = "originalName", target = "originalFirstName")
    @Mapping(source = "originalLastName", target = "originalLastName")
    Person toPerson (PersonRequestDto personRequestDto);
}
