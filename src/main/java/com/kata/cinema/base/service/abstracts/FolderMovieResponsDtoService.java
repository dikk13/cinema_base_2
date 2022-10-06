package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.dto.FolderMovieResponsDto;
import com.kata.cinema.base.dto.FolderResponseDto;
import com.kata.cinema.base.dto.MovieResponseDto;

import java.util.List;

public interface FolderMovieResponsDtoService {
   List<FolderMovieResponsDto> getFolderMovieResponsDtoListByUserId (Long userId);
   FolderMovieResponsDto getFolderMovieResponsDtoById (Long folderMovieId);
   List<FolderResponseDto> getFolderByUser(Long userId);
}
