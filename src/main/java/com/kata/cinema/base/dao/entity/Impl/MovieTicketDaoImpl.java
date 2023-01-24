package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.MovieTicketDao;
import com.kata.cinema.base.dto.MovieReleaseAndTimeDto;
import com.kata.cinema.base.models.MovieTicket;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class MovieTicketDaoImpl extends AbstractDaoImpl<Long, MovieTicket> implements MovieTicketDao {
    @Override
    public List<MovieTicket> movieTicketEndShowDate(LocalDate date) {
        return entityManager.createQuery("select m from MovieTicket m WHERE m.endShowDate =:date", MovieTicket.class)
                .setParameter("date", date)
                .getResultList();
    }
}
