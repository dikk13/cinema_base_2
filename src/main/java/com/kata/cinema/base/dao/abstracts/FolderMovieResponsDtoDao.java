package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.dto.FolderMovieResponsDto;
import com.kata.cinema.base.dto.FolderResponseDto;

import java.util.List;

public interface FolderMovieResponsDtoDao {
    FolderMovieResponsDto getFolderMovieResponsDtoById(Long folderMovieId);
    List<FolderResponseDto> getFolderByUser(Long userId);
}
