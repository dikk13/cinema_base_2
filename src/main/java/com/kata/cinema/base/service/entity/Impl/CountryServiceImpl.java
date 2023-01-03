package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.models.Comments;
import com.kata.cinema.base.models.Country;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.CountryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CountryServiceImpl extends AbstractServiceImpl<Long, Country> implements CountryService {


    protected CountryServiceImpl(AbstractDao<Long, Country> abstractDao) {
        super(abstractDao);
    }

    @Override
    public List<Comments> getAllCountriesById(long id) {
        return null;
    }

}

