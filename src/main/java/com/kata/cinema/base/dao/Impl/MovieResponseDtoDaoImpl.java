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
}



