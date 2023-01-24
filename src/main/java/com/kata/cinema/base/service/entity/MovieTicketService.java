package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.MovieTicket;

import java.time.LocalDate;
import java.util.List;

public interface MovieTicketService extends AbstractService<Long, MovieTicket> {

    List<MovieTicket> movieTicketEndShowDate (LocalDate date);
}
