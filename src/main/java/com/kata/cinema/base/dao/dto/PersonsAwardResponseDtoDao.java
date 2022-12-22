package com.kata.cinema.base.dao.dto;

import com.kata.cinema.base.dto.response.AwardResponseDto;

import java.util.List;

public interface PersonsAwardResponseDtoDao {
    List<AwardResponseDto> getPersonsAwards(Long id);

}
