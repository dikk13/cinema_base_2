package com.kata.cinema.base.service.entity.Impl;


import com.kata.cinema.base.dao.entity.CountryDao;
import com.kata.cinema.base.dao.entity.MovieDao;
import com.kata.cinema.base.dto.MovieReleaseAndTimeDto;
import com.kata.cinema.base.dto.SearchMovieDto;
import com.kata.cinema.base.dto.request.MovieRequestDto;
import com.kata.cinema.base.exception.CountryNotFoundException;
import com.kata.cinema.base.exception.MovieIdNotFoundException;
import com.kata.cinema.base.mappers.MovieMapper;
import com.kata.cinema.base.models.Country;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.GenreService;
import com.kata.cinema.base.service.entity.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieServiceImpl extends AbstractServiceImpl<Long, Movie> implements MovieService {

    private final MovieDao movieDao;
    private final GenreService genreService;
    private final MovieMapper movieMapper;
    private final CountryDao countryDao;

    @Autowired
    protected MovieServiceImpl(MovieDao movieDao, GenreService genreService, MovieMapper movieMapper,
                               CountryDao countryDao) {
        super(movieDao);
        this.movieDao = movieDao;
        this.genreService = genreService;
        this.movieMapper = movieMapper;
        this.countryDao = countryDao;
    }

    @Override
    public List<SearchMovieDto> titleMovie(String name) {
        return movieDao.titleMovie(name);
    }

    @Override
    public List<MovieReleaseAndTimeDto> movieRelease(LocalDate date) {
        return movieDao.movieRelease(date);
    }

    @Override
    @Transactional
    public void updateById(Long id, MovieRequestDto movie) {
        Optional<Movie> optionalMovie = getById(id);
        if (optionalMovie.isPresent()) {
           Movie movieToUpdate = movieMapper.toMovie(movie);
           movieDao.update(movieToUpdate);
        }
    }

    @Override
    public Movie getMovieById(Long movieId) {
        Optional<Movie> movie = movieDao.getById(movieId);
        if (movie.isPresent()) {
            return movie.get();
        } else {
            throw new NullPointerException("Фильм не найден");
        }
    }

    @Override
    @Transactional
    public void addCountriesToMovie(Long movieId, List<Long> countryId) {
        Optional<Movie> movieOptional = movieDao.getById(movieId);
        if (movieOptional.isEmpty()) {
            throw new MovieIdNotFoundException(String.format("Movie (id = %d) is not found", movieId));
        }
        Set<Country> countryToAddSet = countryId.stream().map(countryDao::getById)
                .map(cO -> cO.orElseThrow(() -> new CountryNotFoundException("One or more country is not found")))
                .collect(Collectors.toSet());
        Movie movie = movieOptional.get();
        Set<Country> movieCountries = movie.getCountry();
        movieCountries.addAll(countryToAddSet);
        movie.setCountry(movieCountries);
        movieDao.update(movie);
    }
}
