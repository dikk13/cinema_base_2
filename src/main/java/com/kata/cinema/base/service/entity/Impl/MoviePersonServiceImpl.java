package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.MoviePersonDao;
import com.kata.cinema.base.models.MoviePerson;
import com.kata.cinema.base.models.Profession;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.MoviePersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MoviePersonServiceImpl extends AbstractServiceImpl<Long, MoviePerson> implements MoviePersonService {

    private final MoviePersonDao moviePersonDao;

    @Autowired
    protected MoviePersonServiceImpl(MoviePersonDao moviePersonDao) {
        super(moviePersonDao);
        this.moviePersonDao = moviePersonDao;
    }

    public boolean isProfessionIsBeingUsed(Profession profession) {
        return moviePersonDao.isProfessionIsBeingUsed(profession);
    }

    @Override
    public Optional<MoviePerson> getMoviePersonByMovieIdPersonIdProfessionId(Long movieId, Long personId, Long professionId) {
        return moviePersonDao.getMoviePersonByMovieIdPersonIdProfessionId(movieId, personId, professionId);
    }

    @Transactional
    @Override
    public void updateById(Long id, MoviePerson moviePerson) {
        if (this.getById(id).isPresent()) {
            MoviePerson oldMoviePerson1 = this.getById(id).get();
            moviePerson.setId(id);
            moviePerson.setMovie(oldMoviePerson1.getMovie());
            moviePerson.setPerson(oldMoviePerson1.getPerson());
            moviePerson.setProfession(oldMoviePerson1.getProfession());
            moviePerson.setType(oldMoviePerson1.getType());
            moviePerson.setNameRole(oldMoviePerson1.getNameRole());
        }
    }
}
