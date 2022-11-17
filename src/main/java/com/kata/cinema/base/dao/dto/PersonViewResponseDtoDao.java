package com.kata.cinema.base.dao.dto;

import com.kata.cinema.base.dto.response.PersonViewResponseDto;
import com.kata.cinema.base.dto.response.ProfessionResponseDto;
import com.kata.cinema.base.models.Person;

import java.util.List;

public interface PersonViewResponseDtoDao {
    PersonViewResponseDto getPersonViewResponseDto(Long id);
    List<ProfessionResponseDto> getProfessionResponseDtoListByPerson(Person person);
    Long getMovieCountByPerson(Person person);
}
