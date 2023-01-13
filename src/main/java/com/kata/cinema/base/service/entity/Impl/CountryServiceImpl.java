package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dao.entity.CountryDao;
import com.kata.cinema.base.models.Country;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CountryServiceImpl extends AbstractServiceImpl<Long, Country> implements CountryService {

    private final CountryDao countryDao;

    @Autowired
    protected CountryServiceImpl(CountryDao countryDao) {
        super(countryDao);
        this.countryDao = countryDao;
    }
}

