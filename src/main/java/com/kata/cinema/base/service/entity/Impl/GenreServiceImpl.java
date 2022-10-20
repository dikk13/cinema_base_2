package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dao.entity.GenreDao;
import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.GenreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Transactional
@Service
public class GenreServiceImpl extends AbstractServiceImpl<Long, Genre> implements GenreService {

    private final GenreDao genreDao;

    public GenreServiceImpl(AbstractDao<Long, Genre> abstractDao, GenreDao genreDao) {
        super(abstractDao);
        this.genreDao = genreDao;
    }

    public Map<Long, List<String>> getGenresMap(String moviesId) {
        return genreDao.getGenresMap(moviesId);
    }

    @Override
    public String getGenresOfMovieByMovieId(Long id) {
        List<String> genresList = getGenresMap("(" + id + ")").get(id);

        return genresList.stream().map(g -> g + ", ").collect(Collectors.joining())
                .substring(0, genresList.stream().mapToInt(s -> s.length() + 2).sum() - 2);
    }


}
