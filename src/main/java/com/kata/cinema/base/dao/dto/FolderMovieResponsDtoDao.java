package com.kata.cinema.base.dao.dto;

import com.kata.cinema.base.dto.response.FolderMovieResponsDto;
import com.kata.cinema.base.dto.response.FolderResponseDto;

import java.util.List;
import java.util.Optional;

public interface FolderMovieResponsDtoDao {
    Optional<FolderMovieResponsDto> getFolderMovieResponsDtoById(Long folderMovieId);
    List<FolderResponseDto> getFolderByUser(Long userId);
}
