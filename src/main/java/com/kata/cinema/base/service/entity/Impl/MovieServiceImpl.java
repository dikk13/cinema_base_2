package com.kata.cinema.base.service.entity.Impl;


import com.kata.cinema.base.dao.entity.MovieDao;
import com.kata.cinema.base.dto.SearchMovieDto;
import com.kata.cinema.base.dto.request.MovieRequestDto;
import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.GenreService;
import com.kata.cinema.base.service.entity.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieServiceImpl extends AbstractServiceImpl<Long, Movie> implements MovieService {

    private final MovieDao movieDao;
    private final GenreService genreService;

    @Autowired
    protected MovieServiceImpl(MovieDao movieDao, GenreService genreService) {
        super(movieDao);
        this.movieDao = movieDao;
        this.genreService = genreService;
    }

    @Override
    public List<SearchMovieDto> titleMovie(String name) {
        return movieDao.titleMovie(name);
    }

    @Override
    @Transactional
    public void updateById(Long id, MovieRequestDto movie) {
        Optional<Movie> optionalMovie = getById(id);
        if (optionalMovie.isPresent()) {
           Movie movieToUpdate = optionalMovie.get();
           movieToUpdate.setName(movie.getName());
           movieToUpdate.setCountries(movie.getCountries());
           movieToUpdate.setDateRelease(movie.getDateRelease());
           movieToUpdate.setRars(movie.getRars());
           movieToUpdate.setMpaa(movie.getMpaa());
           movieToUpdate.setTime(movie.getTime());
           movieToUpdate.setDescription(movie.getDescription());
           movieToUpdate.setOriginalName(movie.getOriginalName());
           List<Genre> allGenresList = genreService.getAll();
           List<Genre> genresToUpdate = null;
           for (Genre genre : allGenresList) {
               if (movie.getGenreIds().contains(genre.getId())) {
                   genresToUpdate.add(genre);
               }
           }
           movieToUpdate.setGenres(genresToUpdate);
           movieDao.update(movieToUpdate);
        }
    }

}
