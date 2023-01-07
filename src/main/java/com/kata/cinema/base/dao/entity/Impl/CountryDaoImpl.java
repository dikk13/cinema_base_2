package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.CountryDao;
import com.kata.cinema.base.models.Country;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryDaoImpl extends AbstractDaoImpl<Long, Country> implements CountryDao {

}
