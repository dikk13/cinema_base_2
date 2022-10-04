package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.MovieResponseDtoDao;
import com.kata.cinema.base.dto.MovieResponseDto;
import com.kata.cinema.base.models.enums.Category;
import com.kata.cinema.base.models.enums.CharacterType;
import com.kata.cinema.base.models.enums.ShowType;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.*;

@Repository
public class MovieResponseDtoDaoImpl implements MovieResponseDtoDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<MovieResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.MovieResponseDto (m.id, m.name, m.originalName, m.time, m.dateRelease, m.countries) " +
                "from Movie m where m.id in (select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: folder_movies_id) "
                        + buildShowTypeQueryString((ShowType) parameters.get("showType")), MovieResponseDto.class)
                .setParameter("folder_movies_id", parameters.get("folderMovieId"))
                .setFirstResult((currentPage - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage)
                .getResultList();
    }

    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        return (long) entityManager.createQuery("select m.id from Movie m where m.id in " +
                        "(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: folder_movies_id) "
                        + buildShowTypeQueryString((ShowType) parameters.get("showType")), Long.class)
                .setParameter("folder_movies_id", parameters.get("folderMovieId")).getResultList().size();
    }

        private String buildShowTypeQueryString (ShowType showType) {
        StringBuilder showTypeString = new StringBuilder();
        switch (showType) {

            case VIEWED -> showTypeString.append("and m.id in (select fmm.id from FolderMovie fm join fm.movies fmm where ")
                    .append("fm.category = " + Category.VIEWED_MOVES.ordinal() + " and fm.user.id =")
                    .append("(select fm.user.id from fm where fm.id =: folder_movies_id)) ");

            case NOT_VIEWED -> showTypeString.append("and m.id not in (select fmm.id from FolderMovie fm join fm.movies fmm where ")
                    .append("fm.category = " + Category.VIEWED_MOVES.ordinal() + " and fm.user.id =")
                    .append("(select fm.user.id from fm where fm.id =: folder_movies_id)) ");

            case ALL -> {
                return "";
            }
        }
        return showTypeString.toString();
    }

    @Override
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

    @Override
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

    @Override
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



