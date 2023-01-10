package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.GenreDao;
import com.kata.cinema.base.dto.response.GenreResponseDto;
import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.models.enums.CharacterType;
import org.hibernate.query.Query;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoImpl extends AbstractDaoImpl<Long, Genre> implements GenreDao {

    @Override
    public List<GenreResponseDto> getAllGenreResponseDto() {
        return entityManager.
                createQuery("select new com.kata.cinema.base." +
                        "dto.response.GenreResponseDto(g.id, g.name)" +
                        " from Genre g ", GenreResponseDto.class).getResultList();
    }


    //TODO переписать на transformer
    public Map<Long, List<String>> getGenresMap(String moviesId) {
        Map<Long, List<String>> genreMap = new HashMap<>();
        entityManager.createQuery("select m.id, mg.name from Movie m join m.genres mg where m.id in " + moviesId + "and mg.name='Жанр' and mg.typeCharacter =: characterType")
                .setParameter("characterType", CharacterType.MAIN_CHARACTER.name())
                .unwrap(Query.class)
                .setResultTransformer(
                        new ResultTransformer() {
                            @Override
                            public Object transformTuple(Object[] objects,
                                                         String[] strings) {
                                genreMap.put((Long) objects[0], new ArrayList<>());
                                genreMap.get((Long) objects[0]).add(objects[1] + " " + objects[2]);
                                return genreMap;
                            }

                            @Override
                            public List transformList(List list) {
                                return null;
                            }
                        })
                .getSingleResult();
        return genreMap;
    }

}
