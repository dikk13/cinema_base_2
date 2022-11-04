package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dao.entity.AvailableOnlineMovieDAO;
import com.kata.cinema.base.models.AvailableOnlineMovie;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.AvailableOnlineMovieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AvailableOnlineMovieServiceImpl extends AbstractServiceImpl<Long, AvailableOnlineMovie> implements AvailableOnlineMovieService {
    private final AvailableOnlineMovieDAO availableOnlineMovieDAO;
    protected AvailableOnlineMovieServiceImpl(AbstractDao<Long, AvailableOnlineMovie> abstractDao, AvailableOnlineMovieDAO availableOnlineMovieDAO) {
        super(abstractDao);
        this.availableOnlineMovieDAO = availableOnlineMovieDAO;
    }
}
