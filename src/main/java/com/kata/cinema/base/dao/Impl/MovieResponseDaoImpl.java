package com.kata.cinema.base.dao.Impl;


import com.kata.cinema.base.dao.abstracts.MovieResponseDao;
import com.kata.cinema.base.models.*;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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


        // Рабочий вариант но не работает сортировка
//        EntityGraph entityGraph = entityManager.getEntityGraph("graph_FolderMovie.Movies.Genres.Persons");
//        TypedQuery <FolderMovie> query = entityManager.createQuery("select fm from FolderMovie fm where fm.id =: id", FolderMovie.class)
//                .setParameter("id", folderMovieId)
//                .setHint("javax.persistence.fetchgraph", entityGraph);



//         Рабочий вариант с кривыми жанрами
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("movieResponseDtoGraph");  // Параметризован чем (Movie?)
        TypedQuery<Movie> query = entityManager
                .createQuery("select m from Movie m where m.id in " +
                        "(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: id)" + sorted(sortMovieFolder), Movie.class)
                .setParameter("id", folderMovieId)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .setFirstResult((pageNumber - 1) * itemsOnPage)
                .setMaxResults(itemsOnPage);  // fetchgraph
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

                // Еще надо добавить :
                // RATING("Рейтинг"),
                // MY_SCORE("Моя оценка"),
                // COUNT_SCORE("Число оценок"),
            }
        }
        return "order by m.id asc";
    }
}
