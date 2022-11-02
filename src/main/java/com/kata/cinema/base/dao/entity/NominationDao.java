package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.dto.response.NominationResponseDto;
import com.kata.cinema.base.models.Nomination;

import java.util.List;

public interface NominationDao extends AbstractDao<Long, Nomination> {
    List<NominationResponseDto> toDtoList();
}
