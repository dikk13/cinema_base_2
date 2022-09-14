package com.kata.cinema.base.dao.Impl;


import com.kata.cinema.base.dao.abstracts.MovieResponseDtoDao;
import com.kata.cinema.base.dto.MovieResponseDto;
import com.kata.cinema.base.models.Movie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MovieResponseDtoDaoImpl implements MovieResponseDtoDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<MovieResponseDto> getMovieListByFolderMovieId(Long folderMovieId) {

        System.out.println("try get movies for folderMovie " + folderMovieId);
        TypedQuery<Movie> query = entityManager.createQuery("select m from Movie m where m.id in (select m.id from FolderMovie fm join fm.movies m where fm.id =: id) order by m.id asc", Movie.class);
        query.setParameter("id", folderMovieId);
        System.out.println(query.getResultList());


//        TypedQuery<Movie> query = entityManager.createQuery("select m from Movie m where m.id in (select fm.id from FolderMovie fm join fm.movies where fm.id =: id)", Movie.class);
//        query.setParameter("id", folderMovieId);
//        System.out.println(query.getResultList());
        // select * from folder_movies_to_movie left join movie on movies_id = id where folder_movies_to_movie.folder_id = 2;
        return null;
    }
}
