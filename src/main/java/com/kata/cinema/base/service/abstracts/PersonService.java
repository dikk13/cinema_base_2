package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.dto.search.SearchPersonDto;
import com.kata.cinema.base.models.Person;

import java.util.List;


public interface PersonService extends AbstractService<Long, Person> {

    public List<SearchPersonDto> namePerson();
}
