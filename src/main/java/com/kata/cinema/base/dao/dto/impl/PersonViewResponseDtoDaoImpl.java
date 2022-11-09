package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.PersonViewResponseDtoDao;
import com.kata.cinema.base.dto.response.PersonViewResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PersonViewResponseDtoDaoImpl implements PersonViewResponseDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PersonViewResponseDto getPersonViewResponseDto(Long id) {
        return (PersonViewResponseDto) entityManager.createQuery("" +
                "select new com.kata.cinema.base.dto.response.PersonViewResponseDto(" +
                "person.id," +
                "person.height," +
                "person.dateOfBirth," +
                "person.placeOfBirth," +
                "person.photoUrl," +
                "person.firstName," +
                "person.lastName," +
                "person.originalFirstName," +
                "person.originalLastName) from Person as person where person.id = :personId")
                .setParameter("personId", id)
                .getSingleResult();
    }
}
