package com.kata.cinema.base.config.init;


import com.kata.cinema.base.models.Collection;
import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.RARS;
import com.kata.cinema.base.service.abstracts.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;


public class TestDataInitializer {

    //TODO передалать на использование сервисов
    @PersistenceContext
    private EntityManager entityManager;

    private final GenreService genreService;

    private static final int countMovieList = 100;
    private static final int countCollection = 20;
    private static final int countGenre = 10;

    private final List<Genre> genreList = new ArrayList<>();


    public TestDataInitializer(GenreService genreService) {
        this.genreService = genreService;
    }

    public void movieInit() {
        Movie movie = new Movie();
        Random random = new Random();
        List<Integer> movieList;
        for (int i = 0; i < countMovieList; i++) {
            movieList = Collections.singletonList(i);
            movie.setName(String.valueOf(movieList));
        }
        String descript = """
                            описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма
                            описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма
                            описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма
                            описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма описание фильма
                            """;
        movie.setDescription(descript);
        List<Genre> genreListMovie = new ArrayList<>();
        List<Genre> genres = genreList;
        for (int i = 0; i < 3; i++) {
            int randomIndex = random.nextInt(genres.size());
            Genre randomElement = genres.get(randomIndex);
            genreListMovie.add(randomElement);
            genres.remove(randomIndex);
        }

        movie.setDateRelease(String.valueOf(random.ints(1990, 2023)));
        movie.setTime(String.valueOf(random.ints(100, 181)));
        movie.setMpaa(MPAA.valueOf(String.valueOf(random.ints(MIN_VALUE, MAX_VALUE))));
        movie.setRars(RARS.valueOf(String.valueOf(random.ints(MIN_VALUE, MAX_VALUE))));
//        movie.setGenres(genreListMovie);
        entityManager.persist(movie);

    }

    public void genreInit() {
        for (int i = 0; i < countGenre; i++) {
            Genre genre = new Genre();
            genre.setName(String.format("Жанр%s", i));
            genreService.create(genre);
            genreList.add(genre);
        }
    }

    public void collectionInit() {
        Random random = new Random();
        Collection collection = new Collection();
        List<Boolean> collectionList;
        for (int i = 0; i < countCollection; i++) {
            if (i < 5) {
                collectionList = Collections.singletonList(false);
            } else {
                collectionList = Collections.singletonList(true);
            }
        }
        collection.setMovies((List<Movie>) random.ints(5, 16));
        entityManager.persist(collection);
    }

    private void init() {
        genreInit();
        movieInit();
        collectionInit();
    }
}
