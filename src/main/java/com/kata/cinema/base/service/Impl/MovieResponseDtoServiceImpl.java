package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.MovieResponseDao;
import com.kata.cinema.base.dto.MovieResponseDto;
import com.kata.cinema.base.mappers.MovieResponseDtoMapper;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.service.abstracts.MovieResponseDtoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieResponseDtoServiceImpl implements MovieResponseDtoService {

    private final MovieResponseDao movieResponseDao;
    private final MovieResponseDtoMapper movieResponseDtoMapper;

    public MovieResponseDtoServiceImpl(MovieResponseDao movieResponseDao, MovieResponseDtoMapper movieResponseDtoMapper) {
        this.movieResponseDao = movieResponseDao;
        this.movieResponseDtoMapper = movieResponseDtoMapper;
    }

    @Override
    public List<MovieResponseDto> getMovieResponseDtoListByFolderMovieId(
            Long folderMovieId,
            String sortMovieFolder,
            Integer pageNumber,
            Integer itemsOnPage,
            String showType) {

        List<Movie> movieList = movieResponseDao.getMovieListByFolderMovieId(folderMovieId, sortMovieFolder, pageNumber, itemsOnPage, showType);
        List<MovieResponseDto> resultedList = movieResponseDtoMapper.mapListOfMoviesToDto(movieList);
        return resultedList;
    }
}