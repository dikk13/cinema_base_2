package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.Nomination;

public interface NominationService extends AbstractService<Long, Nomination>{
    Nomination findNomination(Long id);
}
