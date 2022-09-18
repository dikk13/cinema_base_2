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
        EntityGraph entityGraph = entityManager.getEntityGraph("movieResponseDtoGraph");
        TypedQuery<Movie> query = entityManager
                .createQuery("select m from Movie m where m.id in " +
                        "(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: id) order by m.id asc", Movie.class)
                .setParameter("id", folderMovieId).setHint("javax.persistence.fetchgraph", entityGraph);
//        TypedQuery<Genre> query2 = entityManager
//                .createQuery("select mg from Movie m join m.genres mg where m.id in " +
//                        "(select fmm.id from FolderMovie fm join fm.movies fmm where fm.id =: id) order by m.id asc", Genre.class).setParameter("id", folderMovieId);
//        System.out.println(query2.getResultList());


//Query query = entityManager.createQuery("select fm.movies from FolderMovie fm where fm.id =: id", Movie.class).setParameter("id", folderMovieId);

        System.out.println("cp");
//        Query query1 = entityManager.createQuery("select g from Genre g where g.id =: id", Genre.class).setParameter("id", 1L);
//        System.out.println(query1.getResultList());
//        List<Movie> movies = query.getResultList();
//        for (Movie movie: movies) {
//            System.out.println(movie.getGenres());
//        }
        System.out.println("DONE");

//        System.out.println(query.getResultList());

//        TypedQuery<Movie> query = entityManager.createQuery("select m from Movie m where m.id in (select fm.id from FolderMovie fm join fm.movies where fm.id =: id)", Movie.class);
//        query.setParameter("id", folderMovieId);
//        System.out.println(query.getResultList());
        // select * from folder_movies_to_movie left join movie on movies_id = id where folder_movies_to_movie.folder_id = 2;
        return query.getResultList();
//        return null;
    }
}
