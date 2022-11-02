package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.PersonDao;
import com.kata.cinema.base.dto.SearchPersonDto;
import com.kata.cinema.base.models.Person;
import com.kata.cinema.base.models.enums.CharacterType;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PersonDaoImpl extends AbstractDaoImpl<Long, Person> implements PersonDao {


    public List<SearchPersonDto> namePerson(String firstName) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.SearchPersonDto(p.id, p.firstName, p.lastName)"
                        + " from Person p WHERE p.firstName LIKE :firstName", SearchPersonDto.class)
                .setParameter("firstName", firstName + "%").setMaxResults(3).getResultList();
    }

    //TODO переписать на трансформер
    public Map<Long, List<String>> getProducersMap(String moviesId) {
        Map<Long, List<String>> producersMap = new HashMap<>();
        entityManager.createQuery("select m.id, mp.person.firstName, mp.person.lastName from Movie m join m.moviePerson mp where m.id in "
                        + moviesId + " and mp.profession.name = 'Режиссер' and mp.typeCharacter =: characterType")
                .setParameter("characterType", CharacterType.NO_CHARACTER_MOVIE.name())
                .unwrap(Query.class)
                .setResultTransformer(
                        new ResultTransformer() {
                            @Override
                            public Object transformTuple(
                                    Object[] tuples,
                                    String[] aliases) {

                                producersMap.put((Long) tuples[0], new ArrayList<>());
                                producersMap.get((Long) tuples[0]).add(tuples[1] + " " + tuples[2]);
                                return producersMap;
                            }

                            @Override
                            public List transformList(List tuples) {
                                return null;
                            }
                        })
                .getSingleResult();
        return producersMap;

    }


    //TODO переписать на трансформер
    public Map<Long, List<String>> getActorsMap(String moviesId) {
        Map<Long, List<String>> actorsMap = new HashMap<>();
        entityManager.createQuery("select m.id, mp.person.firstName, mp.person.lastName from Movie m join m.moviePerson mp where m.id in "
                        + moviesId + " and mp.profession.name = 'Актер' and mp.typeCharacter =: characterType")
                .setParameter("characterType", CharacterType.MAIN_CHARACTER.name())
                .unwrap(Query.class)
                .setResultTransformer(
                        new ResultTransformer() {
                            @Override
                            public Object transformTuple(
                                    Object[] tuples,
                                    String[] aliases) {

                                actorsMap.put((Long) tuples[0], new ArrayList<>());
                                actorsMap.get((Long) tuples[0]).add(tuples[1] + " " + tuples[2]);
                                return actorsMap;
                            }

                            @Override
                            public List transformList(List list) {
                                return null;
                            }
                        })
                .getSingleResult();
        return actorsMap;
    }
}
