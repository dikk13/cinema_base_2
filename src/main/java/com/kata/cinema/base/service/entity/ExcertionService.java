package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.Excertion;

public interface ExcertionService extends AbstractService<Long, Excertion> {

    void updateById(Long id, Excertion excertion);
}
