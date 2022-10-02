package com.kata.cinema.base.dao.Impl;


import com.kata.cinema.base.dao.abstracts.MovieResponseDtoDao;
import com.kata.cinema.base.dto.MovieResponseDto;
import com.kata.cinema.base.mappers.MovieResponseDtoMapper;
import com.kata.cinema.base.models.*;
import com.kata.cinema.base.models.enums.Category;
import com.kata.cinema.base.models.enums.ShowType;
import com.kata.cinema.base.models.enums.SortMovieFolderType;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.*;

@Repository
public class MovieResponseDtoDaoImpl implements MovieResponseDtoDao {

    @PersistenceContext
    EntityManager entityManager;
    private final MovieResponseDtoMapper movieResponseDtoMapper;

    public MovieResponseDtoDaoImpl(MovieResponseDtoMapper movieResponseDtoMapper) {
        this.movieResponseDtoMapper = movieResponseDtoMapper;
    }

    @Override
    public List<MovieResponseDto> getItemsDto(Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        List<Long> moviesIdQueue = getMoviesIdQueue(currentPage, itemsOnPage, parameters);      //  1. получение id фильмов с учетом сортировки, фильтра и пагинации

        if (moviesIdQueue.size() != 0) {
        Map<Long, Movie> movieMap = new LinkedHashMap<>();

        for (Long movieId: moviesIdQueue) {
            movieMap.put(movieId, null);
        }

        List<Movie> movieList = getMoviesList(movieMap.keySet().toString());                        // 2. получение объектов Movie по id из п.1

        for (Movie movie : movieList) {                                                             // 3. сортировка набора из п.2 в соответствии с порядком из п.1
            movieMap.put(movie.getId(), movie);
        }

        return movieResponseDtoMapper.mapListOfMoviesToDto(new ArrayList<>(movieMap.values()));     // 4. получение объектов MovieDto из п.3
        }

        return new ArrayList<>();                                                                   // если данные не получены - отдаем пустой список
    }


    @Override
    public Long getResultTotal(Map<String, Object> parameters) {
        return (long) entityManager.createQuery(buildQueryStringForQueueGeneration(parameters), Long.class)
                .setParameter("folder_movies_id", parameters.get("folderMovieId")).getResultList().size();
    }


    private List <Long> getMoviesIdQueue (Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        TypedQuery <Long> query = entityManager.createQuery(buildQueryStringForQueueGeneration(parameters), Long.class)
                .setParameter("folder_movies_id", parameters.get("folderMovieId"))
                .setFirstResult((currentPage - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage);
        return query.getResultList();
    }

    private String buildQueryStringForQueueGeneration (Map<String, Object> parameters) {
        SortMovieFolderType sortMovieFolderType = (SortMovieFolderType) parameters.get("sortingType");
        ShowType showType = (ShowType) parameters.get("showType");
        StringBuilder resultedRequestString = new StringBuilder();
        switch (sortMovieFolderType) {

            case NAME -> resultedRequestString.append("select m.id from Movie m where m.id in ")
                    .append ("(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: folder_movies_id) ")
                    .append(buildShowTypeQueryString(showType))
                    .append("group by m.id order by m.name asc");

            case ORIGINAL_NAME -> resultedRequestString.append("select m.id from Movie m where m.id in ")
                    .append ("(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: folder_movies_id) ")
                    .append(buildShowTypeQueryString(showType))
                    .append("group by m.id order by m.originalName asc");

            case YEAR -> resultedRequestString.append("select m.id from Movie m where m.id in ")
                    .append ("(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: folder_movies_id) ")
                    .append(buildShowTypeQueryString(showType))
                    .append("group by m.id order by m.dateRelease asc");

            case RATING -> resultedRequestString.append("select m.id from Movie m left outer join Score s on m.id = s.movie.id where m.id in ")
                    .append ("(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: folder_movies_id) ")
                    .append(buildShowTypeQueryString(showType))
                    .append("group by m.id order by avg (s.score) desc nulls last, m.id");

            case COUNT_SCORE -> resultedRequestString.append("select m.id from Movie m left outer join Score s on m.id = s.movie.id where m.id in ")
                    .append ("(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: folder_movies_id) ")
                    .append(buildShowTypeQueryString(showType))
                    .append("group by m.id order by count (s.score) desc nulls last, m.id");

            case MY_SCORE -> resultedRequestString.append("select m.id from Movie m left outer join m.scores mss on m.id = mss.movie.id and mss.user.id = ")
                    .append("(select fm.user.id from FolderMovie fm where fm.id =: folder_movies_id) where m.id in ")
                    .append ("(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: folder_movies_id) ")
                    .append(buildShowTypeQueryString(showType))
                    .append("group by m.id order by avg (mss.score) desc nulls last, m.id");

            case ORDER -> resultedRequestString.append("select m.id from Movie m where m.id in ")
                    .append ("(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: folder_movies_id) ")
                    .append(buildShowTypeQueryString(showType))
                    .append("group by m.id order by m.id asc");
        }
        System.out.println(resultedRequestString);
        return resultedRequestString.toString();
    }

    private String buildShowTypeQueryString (ShowType showType) {
        StringBuilder showTypeString = new StringBuilder();
        switch (showType) {

            case VIEWED -> showTypeString.append("and m.id in (select fmm.id from FolderMovie fm join fm.movies fmm where fm.id = ")
                    .append("(select fm.id from FolderMovie fm where fm.category = ")
                    .append(Category.VIEWED_MOVES.ordinal())
                    .append(" and fm.user.id = ")
                    .append("(select fm.user.id from fm where fm.id =: folder_movies_id))) ");

            case NOT_VIEWED -> showTypeString.append("and m.id not in (select fmm.id from FolderMovie fm join fm.movies fmm where fm.id = ")
                    .append("(select fm.id from FolderMovie fm where fm.category = ")
                    .append(Category.VIEWED_MOVES.ordinal())
                    .append(" and fm.user.id = ")
                    .append("(select fm.user.id from fm where fm.id =: folder_movies_id))) ");

            case ALL -> {
                return "";
            }
        }
        return showTypeString.toString();
    }

    private List<Movie> getMoviesList(String moviesIdSet) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("movieResponseDtoGraph");
        TypedQuery<Movie> query = entityManager.createQuery("select m from Movie m where m.id in "
                        + moviesIdSet.replace('[', '(').replace(']', ')'), Movie.class)
                .setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }
}



