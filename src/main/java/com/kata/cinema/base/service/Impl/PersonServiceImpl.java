package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.AbstractDao;
import com.kata.cinema.base.dao.abstracts.PersonDao;
import com.kata.cinema.base.dto.search.SearchPersonDto;
import com.kata.cinema.base.models.Person;
import com.kata.cinema.base.service.abstracts.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl extends AbstractServiceImpl<Long, Person> implements PersonService {

    private final PersonDao personDao;

    @Autowired
    protected PersonServiceImpl(PersonDao personDao) {
        super(personDao);
        this.personDao = personDao;
    }


    @Override
    public List<SearchPersonDto> namePerson(String firstName) {
        return personDao.namePerson(firstName);
    }
}
