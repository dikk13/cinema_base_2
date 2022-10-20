package com.kata.cinema.base.dao.dto;

import com.kata.cinema.base.dto.response.FolderMovieResponsDto;
import com.kata.cinema.base.dto.response.FolderResponseDto;

import java.util.List;

public interface FolderMovieResponsDtoDao {
    FolderMovieResponsDto getFolderMovieResponsDtoById(Long folderMovieId);
    List<FolderResponseDto> getFolderByUser(Long userId);
}
