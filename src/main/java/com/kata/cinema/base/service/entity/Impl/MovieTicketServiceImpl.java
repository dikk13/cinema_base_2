package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.MovieTicketDao;
import com.kata.cinema.base.models.MovieTicket;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.MovieTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MovieTicketServiceImpl extends AbstractServiceImpl<Long, MovieTicket> implements MovieTicketService {

    private final MovieTicketDao movieTicketDao;

    @Autowired
    public MovieTicketServiceImpl(MovieTicketDao movieTicketDao){
        super(movieTicketDao);
        this.movieTicketDao = movieTicketDao;
    }
}
