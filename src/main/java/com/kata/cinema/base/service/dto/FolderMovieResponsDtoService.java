package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.dto.response.FolderMovieResponsDto;
import com.kata.cinema.base.dto.response.FolderResponseDto;

import java.util.List;

public interface FolderMovieResponsDtoService {
   List<FolderMovieResponsDto> getFolderMovieResponsDtoListByUserId (Long userId);
   FolderMovieResponsDto getFolderMovieResponsDtoById (Long folderMovieId);
   List<FolderResponseDto> getFolderByUser(Long userId);
}
