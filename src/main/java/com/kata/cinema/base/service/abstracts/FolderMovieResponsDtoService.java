package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.dto.FolderMovieResponsDto;

import java.util.List;

public interface FolderMovieResponsDtoService {
    public List<FolderMovieResponsDto> getFolderMovieResponsDtoListByUserId (Long userId);
    public FolderMovieResponsDto getFolderMovieResponsDtoById (Long folderMovieId);
}
