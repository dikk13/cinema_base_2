package com.kata.cinema.base.dao.Impl;


import com.kata.cinema.base.dao.abstracts.MovieResponseDao;
import com.kata.cinema.base.dto.MovieResponseDto;
import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.models.Movie;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class MovieResponseDaoImpl implements MovieResponseDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Movie> getMovieListByFolderMovieId(Long folderMovieId) {
        System.out.println("try get movies for folderMovie " + folderMovieId);
        EntityGraph entityGraph = entityManager.getEntityGraph("movieResponseDtoGraph");  // Параметризован чем (Movie?)
        TypedQuery<Movie> query = entityManager
                .createQuery("select m from Movie m where m.id in " +
                        "(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: id) order by m.id asc", Movie.class)
                .setParameter("id", folderMovieId).setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }
}
