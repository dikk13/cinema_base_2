package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dao.entity.AvailableOnlineMovieDAO;
import com.kata.cinema.base.exception.ProfessionNotFountException;
import com.kata.cinema.base.models.AvailableOnlineMovie;
import com.kata.cinema.base.models.ProductionStudio;
import com.kata.cinema.base.models.Profession;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.AvailableOnlineMovieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AvailableOnlineMovieServiceImpl extends AbstractServiceImpl<Long, AvailableOnlineMovie> implements AvailableOnlineMovieService {
    private final AvailableOnlineMovieDAO availableOnlineMovieDAO;
    protected AvailableOnlineMovieServiceImpl(AvailableOnlineMovieDAO availableOnlineMovieDAO) {
        super(availableOnlineMovieDAO);
        this.availableOnlineMovieDAO = availableOnlineMovieDAO;
    }

    @Override
    public Optional<AvailableOnlineMovie> getAvailableOnlineMovieById (Long movieId) {
        return availableOnlineMovieDAO.getAvailableOnlineMovieById(movieId);
    }
}
