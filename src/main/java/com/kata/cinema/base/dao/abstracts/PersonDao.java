package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.dto.search.SearchPersonDto;
import com.kata.cinema.base.models.Person;

import java.util.List;

public interface PersonDao extends AbstractDao<Long, Person> {

    public List<SearchPersonDto> namePerson();

}
