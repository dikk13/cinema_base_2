package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.PersonViewResponseDtoDao;
import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dto.response.PersonViewResponseDto;
import com.kata.cinema.base.models.Person;
import com.kata.cinema.base.service.dto.PersonViewResponseDtoService;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.GenreService;
import com.kata.cinema.base.service.entity.PersonService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonViewResponseDtoServiceImpl extends AbstractServiceImpl<Long, Person> implements PersonViewResponseDtoService {

    private final PersonService personService;
    private final PersonViewResponseDtoDao personViewResponseDtoDao;
    private final GenreService genreService;

    protected PersonViewResponseDtoServiceImpl(@Qualifier("personDaoImpl")AbstractDao<Long, Person> abstractDao, PersonService personService, PersonViewResponseDtoDao personViewResponseDtoDao, GenreService genreService) {
        super(abstractDao);
        this.personService = personService;
        this.personViewResponseDtoDao = personViewResponseDtoDao;
        this.genreService = genreService;
    }

    @Override
    public PersonViewResponseDto getPersonViewResponseDto(Long id) {
        Optional<Person> optionalPerson = personService.getById(id);
        if (optionalPerson.isPresent()) {
            PersonViewResponseDto person = personViewResponseDtoDao.getPersonViewResponseDto(id);
            person.setCountMovie(personViewResponseDtoDao.getMovieCountByPerson(optionalPerson.get()));
            person.setGenres(genreService.getAllGenreResponseDto());
            person.setProfession(personViewResponseDtoDao.getProfessionResponseDtoListByPerson(optionalPerson.get()));
            return personViewResponseDtoDao.getPersonViewResponseDto(id);
        }
        return null;
    }
}
