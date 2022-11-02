package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.dto.response.NominationResponseDto;
import com.kata.cinema.base.models.Nomination;

import java.util.List;

public interface NominationService extends AbstractService<Long, Nomination>{
    Nomination findNomination(Long id);

    List<NominationResponseDto> toDtoList();
}
