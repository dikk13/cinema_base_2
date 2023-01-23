package com.kata.cinema.base.service;

import com.kata.cinema.base.dto.MovieReleaseAndTimeDto;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.MovieTicket;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.service.entity.MovieTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ScheduledTasks {

    private final MovieService movieService;
    private final MovieTicketService movieTicketService;

    @Autowired
    public ScheduledTasks(MovieService movieService, MovieTicketService movieTicketService){
        this.movieService = movieService;
        this.movieTicketService = movieTicketService;
    }

    @Scheduled(fixedRate = 5000)
    public void createMovieTicket(){
        List<Movie> movies = movieService.getAll();
        LocalDate date0 = movies.get(0).getDateRelease();
        System.out.println(date0);
        List<MovieReleaseAndTimeDto> moviesRelease = movieService.movieRelease(date0);
        List<MovieTicket> movieTickets = movieTicketService.getAll();
//        moviesRelease.stream().forEach();
    }
}
