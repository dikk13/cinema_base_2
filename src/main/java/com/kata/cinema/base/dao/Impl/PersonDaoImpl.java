package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.PersonDao;
import com.kata.cinema.base.dto.search.SearchMovieDto;
import com.kata.cinema.base.dto.search.SearchPersonDto;
import com.kata.cinema.base.models.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PersonDaoImpl extends AbstractDaoImpl<Long, Person> implements PersonDao {

    @PersistenceContext
    EntityManager entityManager;


    public List<SearchPersonDto> namePerson(String firstName) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.search.SearchPersonDto(p.id, p.firstName, p.lastName)"
                + " from Person p WHERE p.firstName LIKE :firstName", SearchPersonDto.class)
                .setParameter("firstName", firstName + "%").setMaxResults(3).getResultList();
    }
}
