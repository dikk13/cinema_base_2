package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.PersonViewResponseDtoDao;
import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dto.response.PersonViewResponseDto;
import com.kata.cinema.base.models.Person;
import com.kata.cinema.base.service.dto.PersonViewResponseDtoService;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.PersonService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonViewResponseDtoServiceImpl extends AbstractServiceImpl<Long, Person> implements PersonViewResponseDtoService {

    private final PersonService personService;
    private final PersonViewResponseDtoDao personViewResponseDtoDao;

    protected PersonViewResponseDtoServiceImpl(@Qualifier("personDaoImpl")AbstractDao<Long, Person> abstractDao, PersonService personService, PersonViewResponseDtoDao personViewResponseDtoDao) {
        super(abstractDao);
        this.personService = personService;
        this.personViewResponseDtoDao = personViewResponseDtoDao;
    }

    @Override
    public PersonViewResponseDto getPersonViewResponseDto(Long id) {
        Optional<Person> optionalPerson = personService.getById(id);
        if (optionalPerson.isPresent()) {
            return personViewResponseDtoDao.getPersonViewResponseDto(id);
        }
        return null;
    }
}
