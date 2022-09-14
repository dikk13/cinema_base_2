package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.dto.MovieResponseDto;
import java.util.List;

public interface MovieResponseDtoDao {
    List<MovieResponseDto> getMovieListByFolderMovieId(Long FolderMovieId);
}