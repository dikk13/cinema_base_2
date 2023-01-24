package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.MovieTicketRequestDto;
import com.kata.cinema.base.dto.response.MovieTicketResponseDto;
import com.kata.cinema.base.models.MovieTicket;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface MovieTicketMapper {

    MovieTicket toMovieTicket(MovieTicketRequestDto movieTicketRequestDto);

    MovieTicketResponseDto toDto (MovieTicket movieTicket);

    List<MovieTicketResponseDto> toDTOList(List<MovieTicket> list);

    MovieTicket toMovieTicket(MovieTicketResponseDto movieTicketResponseDto);
}
