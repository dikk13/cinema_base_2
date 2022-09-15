package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.MovieResponseDao;
import com.kata.cinema.base.dto.MovieResponseDto;
import com.kata.cinema.base.mappers.MovieResponseDtoMapper;
import com.kata.cinema.base.models.enums.SortMovieFolderType;
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
    public List<MovieResponseDto> getMovieResponseDtoListByFolderMovieId(Long folderMovieId, String sortMovieFolder) {
        System.out.println(sortMovieFolder);
        List<MovieResponseDto> resultedList = movieResponseDtoMapper.mapListOfMoviesToDto(movieResponseDao.getMovieListByFolderMovieId(folderMovieId));
        switch (sortMovieFolder) {
            case ("NAME") -> resultedList.sort((a, b) -> a.getName().compareTo(b.getName()));
            case ("ORIGINAL_NAME") -> resultedList.sort((a, b) -> a.getOriginalName().compareTo(b.getOriginalName()));
            case ("YEAR") -> resultedList.sort((a, b) -> a.getDateRelease().compareTo(b.getDateRelease()));

            // Еще надо добавить :
            // RATING("Рейтинг"),
            // MY_SCORE("Моя оценка"),
            // COUNT_SCORE("Число оценок"),
        }


        return resultedList;
    }
}