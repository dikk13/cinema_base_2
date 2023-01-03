package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.Comments;
import com.kata.cinema.base.models.Country;
import com.kata.cinema.base.models.Excertion;

import java.util.List;

public interface CountryService extends AbstractService<Long, Country> {

    List<Comments> getAllCountriesById(long id);
}
