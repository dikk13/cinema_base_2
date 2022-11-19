package com.kata.cinema.base.service.entity;

import com.kata.cinema.base.models.FolderMovie;

import java.util.List;
import java.util.Optional;

public interface FolderMovieService extends AbstractService<Long, FolderMovie> {
    public List<FolderMovie> getFolderMovieListByUserId (Long userId);
    public Optional<FolderMovie> getFolderMovieById (Long folderMovieId);
}
