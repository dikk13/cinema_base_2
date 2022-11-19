package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.dto.response.FolderMovieResponsDto;
import com.kata.cinema.base.dto.response.FolderResponseDto;

import java.util.List;
import java.util.Optional;

public interface FolderMovieResponsDtoService {
   List<FolderMovieResponsDto> getFolderMovieResponsDtoListByUserId (Long userId);
   Optional<FolderMovieResponsDto> getFolderMovieResponsDtoById (Long folderMovieId);
   List<FolderResponseDto> getFolderByUser(Long userId);
}
