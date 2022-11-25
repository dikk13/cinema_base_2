package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.dto.response.PersonViewResponseDto;
import com.kata.cinema.base.models.Person;
import com.kata.cinema.base.service.entity.AbstractService;

public interface PersonViewResponseDtoService extends AbstractService<Long, Person> {
    PersonViewResponseDto getPersonViewResponseDto(Long id);
}
