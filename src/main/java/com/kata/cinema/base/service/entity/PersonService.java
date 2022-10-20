package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.dto.SearchPersonDto;
import com.kata.cinema.base.models.Person;

import java.util.List;


public interface PersonService extends AbstractService<Long, Person> {

    List<SearchPersonDto> namePerson(String firstName);
}
