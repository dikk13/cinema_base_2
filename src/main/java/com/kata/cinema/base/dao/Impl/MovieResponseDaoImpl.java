package com.kata.cinema.base.dao.Impl;


import com.kata.cinema.base.dao.abstracts.MovieResponseDao;
import com.kata.cinema.base.models.*;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.*;

@Repository
public class MovieResponseDaoImpl implements MovieResponseDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Movie> getMovieListByFolderMovieId (
            Long folderMovieId,
            String sortMovieFolder,
            Integer pageNumber,
            Integer itemsOnPage) {
        System.out.println("try get movies for folderMovie " + folderMovieId);

        if (sortMovieFolder != null) {
            switch (sortMovieFolder) {
                case ("COUNT_SCORE"):
                case ("RATING"):
                    return getMovieListByFolderMovieId_postSorting(folderMovieId, sortMovieFolder, pageNumber, itemsOnPage);
            }
        }

        /* --- РАБОЧИЙ КОД С НЕ РЕАЛИЗОВАННЫМИ СОРТИРОВКАМИ  COUNT_SCORE("Число оценок"), RATING ("Средняя оценка пользователями) ---*/

        EntityGraph<?> entityGraph = entityManager.getEntityGraph("movieResponseDtoGraph");
        TypedQuery<Movie> query = entityManager
                .createQuery("select m from Movie m " + sorted(sortMovieFolder, folderMovieId.toString())[0] +
                        " where m.id in (select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: id) " + sorted(sortMovieFolder, folderMovieId.toString())[1], Movie.class)
                .setParameter("id", folderMovieId)
                .setHint("javax.persistence.fetchgraph", entityGraph)   // fetchgraph  или loadgraph
                .setFirstResult((pageNumber - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage);

        return query.getResultList();
    }

    @Override
    public List<Movie> getMovieListByFolderMovieId_postSorting(Long folderMovieId, String sortMovieFolder, Integer pageNumber, Integer itemsOnPage) {

        /* -- 1. Получили и сохранили список очередности (!!!) id фильмов -- */

        Query queueOrderQuery = entityManager.createQuery("select m.id, m.name, " + chooseAgrFuncParams(sortMovieFolder) + " (s.score) as agregate_func from Movie m " +
                        "left outer join Score s on m.id = s.movie.id " +
                        "where m.id in " +
                        "(select fm_movies.id from FolderMovie fm join fm.movies fm_movies on m.id = fm_movies.id where fm.id =: folder_movie_id)" +
                        "group by m.id " +
                        "order by agregate_func desc nulls last, m.id")
                .setParameter("folder_movie_id", folderMovieId)
                .setFirstResult((pageNumber - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage);

        List<Object[]> queueOrder = queueOrderQuery.getResultList();

        /* -- 2. Получили объекты фильмов -- */

        EntityGraph<?> entityGraph = entityManager.getEntityGraph("movieResponseDtoGraph");
        TypedQuery<Movie> extractMoviesQuery = entityManager
                .createQuery("select m from Movie m where m.id in " + getIdQueue(queueOrder), Movie.class)
                .setHint("javax.persistence.fetchgraph", entityGraph);   // fetchgraph  или loadgraph

        List<Movie> movieList = extractMoviesQuery.getResultList();

        /* -- 3. Отсортировали объекты фильмов в соответствии с порядком из п.1 -- */

        Map <Long, Movie> sortedMovieList = new LinkedHashMap<>();
        for (Object[] row: queueOrder) {
            sortedMovieList.put((Long) row[0], null);
        }

        for (Movie movie: movieList) {
            sortedMovieList.put(movie.getId(), movie);
        }

        return new ArrayList<>(sortedMovieList.values());
    }

    @Override
    public String[] sorted(String sortingParameters, String folderMovieId) {
        String[] requestParams = new String[2];
        if (sortingParameters != null) {
            switch (sortingParameters) {
                case ("NAME"):
                    requestParams[0] = "";
                    requestParams[1] = "order by m.name";
                    return requestParams;
                case ("ORIGINAL_NAME"):
                    requestParams[0] = "";
                    requestParams[1] = "order by m.originalName";
                    return requestParams;
                case ("YEAR"):
                    requestParams[0] = "";
                    requestParams[1] = "order by m.dateRelease";
                    return requestParams;
                case ("MY_SCORE"):
                    requestParams[0] = "left join m.scores mss on m.id = mss.movie.id and mss.user.id= (select fm.user.id from FolderMovie fm where fm.id = " + folderMovieId + ")";
                    requestParams[1] = "order by mss.score desc nulls last, m.id";
                    return requestParams;
            }
        }
        requestParams[0] = "";
        requestParams[1] = "order by m.id asc";
        return requestParams;
    }

    @Override
    public String chooseAgrFuncParams(String sortingParameters) {
        return switch (sortingParameters) {
            case ("COUNT_SCORE") -> "count";
            case ("RATING") -> "avg";
            default -> sortingParameters;  // ?
        };
    }

    @Override
    public String getIdQueue(List<Object[]> result) {
        System.out.println("размер списка: " + result.size());
        int i = 1;
        StringBuilder idString = new StringBuilder("(");
        for (Object[] row: result) {
            idString.append(row[0]);
            if (i < result.size()) {
                idString.append(", ");
                i++;
            }
        }
        idString.append(")");
        return idString.toString();
    }


}



