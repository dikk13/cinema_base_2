package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.MovieResponseDtoDao;
import com.kata.cinema.base.dto.MovieResponseDto;
import com.kata.cinema.base.service.abstracts.MovieResponseDtoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieResponseDtoServiceImpl implements MovieResponseDtoService {

    private final MovieResponseDtoDao movieResponseDtoDao;

    public MovieResponseDtoServiceImpl(MovieResponseDtoDao movieResponseDtoDao) {
        this.movieResponseDtoDao = movieResponseDtoDao;
    }

    @Override
    public List<MovieResponseDto> getMovieListByFolderMovieId(Long folderMovieId) {
        return movieResponseDtoDao.getMovieListByFolderMovieId(folderMovieId);
    }
}