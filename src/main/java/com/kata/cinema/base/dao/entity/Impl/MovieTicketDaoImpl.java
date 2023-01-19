package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.MovieTicketDao;
import com.kata.cinema.base.models.MovieTicket;
import org.springframework.stereotype.Repository;

@Repository
public class MovieTicketDaoImpl extends AbstractDaoImpl<Long, MovieTicket> implements MovieTicketDao {
}
