package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.PersonViewResponseDtoDao;
import com.kata.cinema.base.dto.response.PersonViewResponseDto;
import com.kata.cinema.base.dto.response.ProfessionResponseDto;
import com.kata.cinema.base.mappers.ProfessionMapper;
import com.kata.cinema.base.models.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PersonViewResponseDtoDaoImpl implements PersonViewResponseDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    private final ProfessionMapper professionMapper;

    public PersonViewResponseDtoDaoImpl(ProfessionMapper professionMapper) {
        this.professionMapper = professionMapper;
    }

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

    @Override
    public List<ProfessionResponseDto> getProfessionResponseDtoListByPerson(Person person) {
        return professionMapper.toDTOList(
                entityManager.createQuery("select " +
                "distinct mp.profession " +
                "from MoviePerson  mp where mp.person = :person")
                .setParameter("person", person)
                .getResultList()
        );
    }

    @Override
    public Long getMovieCountByPerson(Person person) {
        return (Long) entityManager.createQuery("select count(m.id) from MoviePerson m where m.person = :person")
                .setParameter("person", person)
                .getSingleResult();
    }


}
