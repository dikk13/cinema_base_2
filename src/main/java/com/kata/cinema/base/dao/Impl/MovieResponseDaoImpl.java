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

        EntityGraph<?> entityGraph = entityManager.getEntityGraph("movieResponseDtoGraph");
        TypedQuery<Movie> query = entityManager
                .createQuery("select m from Movie m left join m.scores mss on m.id = mss.movie.id and mss.user.id= (select fm.user.id from FolderMovie fm where fm.id =: id) " +
                        "where m.id in " +
                        "(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: id) " + sorted(sortMovieFolder), Movie.class)
                .setParameter("id", folderMovieId)
                .setHint("javax.persistence.fetchgraph", entityGraph)   // fetchgraph  или loadgraph
                .setFirstResult((pageNumber - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage);

        return query.getResultList();
    }

    @Override
    public String sorted(String sortingParameters) {
        if (sortingParameters != null) {
            switch (sortingParameters) {
                case ("NAME"):
                    return "order by m.name";
                case ("ORIGINAL_NAME"):
                    return "order by m.originalName";
                case ("YEAR"):
                    return "order by m.dateRelease";
                case ("RATING"):
                    return "order by m.mpaa";
                case ("MY_SCORE"):
                    return "order by mss.score desc nulls last";

                // Еще надо добавить :
                // COUNT_SCORE("Число оценок"),
                // RATING ("Средняя оценка пользователями)
            }
        }
        return "order by m.id asc";
    }
}
