package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.Profession;

import java.util.Optional;

public interface ProfessionDao extends AbstractDao<Long, Profession>{
    Optional<Profession> getByName(String profession);

}
