package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.PersonDao;
import com.kata.cinema.base.dto.search.SearchMovieDto;
import com.kata.cinema.base.dto.search.SearchPersonDto;
import com.kata.cinema.base.models.Person;
import com.kata.cinema.base.models.enums.CharacterType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PersonDaoImpl extends AbstractDaoImpl<Long, Person> implements PersonDao {


    public List<SearchPersonDto> namePerson(String firstName) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.search.SearchPersonDto(p.id, p.firstName, p.lastName)"
                + " from Person p WHERE p.firstName LIKE :firstName", SearchPersonDto.class)
                .setParameter("firstName", firstName + "%").setMaxResults(3).getResultList();
    }

    public Map<Long, List<String>> getProducersMap(String moviesId) {
        List <Object[]> testResult2 = entityManager.createQuery("select m.id, mp.person.firstName, mp.person.lastName from Movie m join m.moviePerson mp where m.id in "
                        + moviesId + " and mp.profession.name = 'Режиссер' and mp.typeCharacter =: characterType")
                .setParameter("characterType", String.valueOf(CharacterType.NO_CHARACTER_MOVIE.ordinal()))
                .getResultList();

        Map<Long, List<String>> producersMap = new HashMap<>();
        for (Object[] row : testResult2) {
            if (!producersMap.containsKey((Long) row[0])) {
                producersMap.put((Long) row[0], new ArrayList<>());
            }
            producersMap.get((Long) row[0]).add(row[1] + " " + row[2]);
        }
        return producersMap;
    }

    public Map<Long, List<String>> getActorsMap(String moviesId) {
        List <Object[]> testResult3 = entityManager.createQuery("select m.id, mp.person.firstName, mp.person.lastName from Movie m join m.moviePerson mp where m.id in "
                        + moviesId + " and mp.profession.name = 'Актер' and mp.typeCharacter =: characterType")
                .setParameter("characterType", String.valueOf(CharacterType.MAIN_CHARACTER.ordinal()))
                .getResultList();

        Map<Long, List<String>> actorsMap = new HashMap<>();
        for (Object[] row : testResult3) {
            if (!actorsMap.containsKey((Long) row[0])) {
                actorsMap.put((Long) row[0], new ArrayList<>());
            }
            actorsMap.get((Long) row[0]).add(row[1] + " " + row[2]);
        }
        return actorsMap;
    }
}
