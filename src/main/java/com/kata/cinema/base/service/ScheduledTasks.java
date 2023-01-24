package com.kata.cinema.base.service;

import com.kata.cinema.base.dto.MovieReleaseAndTimeDto;
import com.kata.cinema.base.dto.request.MovieTicketRequestDto;
import com.kata.cinema.base.dto.response.MovieTicketResponseDto;
import com.kata.cinema.base.mappers.MovieTicketMapper;
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

    private final MovieTicketMapper movieTicketMapper;

    @Autowired
    public ScheduledTasks(MovieService movieService, MovieTicketService movieTicketService, MovieTicketMapper movieTicketMapper){
        this.movieService = movieService;
        this.movieTicketService = movieTicketService;
        this.movieTicketMapper = movieTicketMapper;
    }

    @Scheduled(cron = "0 0 6 * * *")
    public void createMovieTicket(){
        List<MovieReleaseAndTimeDto> moviesRelease = movieService.movieRelease(LocalDate.now());
        List<MovieTicketResponseDto> movieTickets = movieTicketMapper.toDTOList(movieTicketService.movieTicketEndShowDate(LocalDate.now()));
        moviesRelease.forEach(x -> movieTicketService.create(movieTicketMapper
                                        .toMovieTicket(new MovieTicketRequestDto(x.getId(),
                                            x.getDateRelease().plusDays(x.getTime())))));
        movieTickets.forEach(x -> movieService.deleteById(x.getMovieId()));
    }
}
