package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.dto.SearchPersonDto;
import com.kata.cinema.base.models.Person;

import java.util.List;

public interface PersonDao extends AbstractDao<Long, Person> {

    List<SearchPersonDto> namePerson(String firstName);

}
