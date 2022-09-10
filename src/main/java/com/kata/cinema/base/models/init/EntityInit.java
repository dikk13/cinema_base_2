package com.kata.cinema.base.models.init;


import com.kata.cinema.base.models.Collection;
import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.RARS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

@ConditionalOnExpression
public class EntityInit {

    @PersistenceContext
    private EntityManager entityManager;

    private final Movie movie;
    private final Genre genre;
    private final Collection collection;

    @Autowired
    public EntityInit(Movie movie, Genre genre, Collection collection) {
        this.movie = movie;
        this.genre = genre;
        this.collection = collection;
    }


    public void MovieInit(Movie movie) {
        Random random = new Random();
        List<Integer> movieList;
        for (int i = 0; i < 100; i++) {
            movieList = Collections.singletonList(i);
            movie.setName(String.valueOf(movieList));
        }
        List<String> genreListmovie =new ArrayList<>(100);
        genreListmovie = (List<String>) random.ints(1, 4);

        movie.setDateRelease(String.valueOf(random.ints(1990, 2023)));
        movie.setTime(String.valueOf(random.ints(100, 181)));
        movie.setMpaa(MPAA.valueOf(String.valueOf(random.ints(MIN_VALUE, MAX_VALUE))));
        movie.setRars(RARS.valueOf(String.valueOf(random.ints(MIN_VALUE, MAX_VALUE))));

        entityManager.persist(movie);

    }

    public void GenreInit(Genre genre) {
        List<String> genreList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            genre.setName(String.valueOf(genreList));
        }
        entityManager.persist(genre);
    }

    public void CollectionInit(Collection collection) {
        Random random = new Random();
        List<Boolean> collectionList;
        for (int i = 0; i < 20; i++) {
            if (i < 5) {
                collectionList = Collections.singletonList(false);
            } else if (i >= 5) {
                collectionList = Collections.singletonList(true);
            }
        }
        collection.setMovies((List<Movie>) random.ints(5, 16));

        entityManager.persist(collection);
    }

}
