package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.MovieTicket;

import java.time.LocalDate;
import java.util.List;

public interface MovieTicketDao extends AbstractDao<Long, MovieTicket>{

    List<MovieTicket> movieTicketEndShowDate(LocalDate date);
}
