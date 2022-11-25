package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dao.entity.PurchasedMovieDAO;
import com.kata.cinema.base.models.PurchasedMovie;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.PurchasedMovieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PurchasedMovieServiceImpl extends AbstractServiceImpl<Long, PurchasedMovie> implements PurchasedMovieService {
    private final PurchasedMovieDAO purchasedMovieDAO;

    protected PurchasedMovieServiceImpl(AbstractDao<Long, PurchasedMovie> abstractDao, PurchasedMovieDAO purchasedMovieDAO) {
        super(abstractDao);
        this.purchasedMovieDAO = purchasedMovieDAO;
    }

}
