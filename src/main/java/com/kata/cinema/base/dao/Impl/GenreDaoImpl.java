package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.GenreDao;
import com.kata.cinema.base.dto.GenreResponseDto;
import com.kata.cinema.base.models.Genre;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoImpl extends AbstractDaoImpl <Long,Genre> implements GenreDao {

    @Override
    public List<GenreResponseDto> getAllGenreResponseDto() {
        return entityManager.
                createQuery("select new com.kata.cinema.base." +
                        "dto.GenreResponseDto(g.id, g.name)" +
                        " from Genre g ", GenreResponseDto.class).getResultList();
    }


    //TODO переписать на transformer
    public Map<Long, List<String>> getGenresMap(String moviesId) {
        List <Object[]> testResult1 = entityManager.createQuery("select m.id, mg.name from Movie m join m.genres mg where m.id in " + moviesId)
                .getResultList();
        Map<Long, List<String>> genresMap = new HashMap<>();
        for (Object[] row : testResult1) {
            if (!genresMap.containsKey((Long) row[0])) {
                genresMap.put((Long) row[0], new ArrayList<>());
            }
            genresMap.get((Long) row[0]).add((String) row[1]);
        }
        return genresMap;
    }


}
