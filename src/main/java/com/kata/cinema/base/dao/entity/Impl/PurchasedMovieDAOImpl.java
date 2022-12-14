package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.PurchasedMovieDAO;
import com.kata.cinema.base.models.PurchasedMovie;
import org.springframework.stereotype.Repository;

@Repository
public class PurchasedMovieDAOImpl extends AbstractDaoImpl<Long, PurchasedMovie> implements PurchasedMovieDAO {
}