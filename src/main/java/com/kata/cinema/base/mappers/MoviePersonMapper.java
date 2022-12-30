package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.CastRequestDto;
import com.kata.cinema.base.dto.response.MoviePersonResponseDto;
import com.kata.cinema.base.models.MoviePerson;
import com.kata.cinema.base.models.Person;
import com.kata.cinema.base.models.Profession;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MoviePersonMapper {
    default MoviePersonResponseDto moviePersonToMoviePersonResponseDto(MoviePerson moviePerson) {
        MoviePersonResponseDto responseDto = new MoviePersonResponseDto();
        responseDto.setType(moviePerson.getType());
        responseDto.setNameCharacter(moviePerson.getNameRole());
        responseDto.setFullName(moviePerson.getPerson().getFirstName() + " " + moviePerson.getPerson().getLastName());
        responseDto.setPersonId(moviePerson.getPerson().getId());
        responseDto.setProfessionId(moviePerson.getProfession().getId());
        responseDto.setOriginalFullName(moviePerson.getPerson().getOriginalFirstName() + " " + moviePerson.getPerson().getOriginalLastName());
        return responseDto;
    }

    default List<MoviePersonResponseDto> moviePersonListToMoviePersonResponseDtoList(List<MoviePerson> moviePersonList) {
        List<MoviePersonResponseDto> moviePersonResponseDtoList = new ArrayList<>();
        for (MoviePerson mp : moviePersonList) {
            moviePersonResponseDtoList.add(moviePersonToMoviePersonResponseDto(mp));
        }
        return moviePersonResponseDtoList;
    }

    default MoviePerson toMoviePerson(CastRequestDto castRequestDto) {
        MoviePerson moviePerson = new MoviePerson();
        Profession profession = new Profession();
        Person person = new Person();
        profession.setId(castRequestDto.getProfessionId());
        moviePerson.setProfession(profession);
        person.setId(castRequestDto.getPersonId());
        moviePerson.setPerson(person);
        moviePerson.setType(castRequestDto.getType());
        moviePerson.setNameRole(castRequestDto.getNameCharacter());
        return moviePerson;
    }
}
