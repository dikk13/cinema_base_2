package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.ProductionStudio;
import com.kata.cinema.base.models.Profession;

import java.util.Optional;

public interface ProfessionService extends AbstractService<Long, Profession> {
    Optional<Profession> getByName(String profession);
    void updateById(Long id, String name);
}

