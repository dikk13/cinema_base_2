package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.dto.MovieResponseDto;

import java.util.List;

public interface MovieResponseDtoService {
    List<MovieResponseDto> getMovieResponseDtoListByFolderMovieId (
            Long FolderMovieId,
            String sortMovieFolder,
            Integer pageNumber,
            Integer itemsOnPage,
            String showType);
}
