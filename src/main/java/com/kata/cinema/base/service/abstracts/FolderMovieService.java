package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.models.FolderMovie;

import java.util.List;

public interface FolderMovieService extends AbstractService<Long, FolderMovie> {
    List<FolderMovie> getFolderMovieListByUserId (Long userId);
    FolderMovie getFolderMovieById (Long folderMovieId);
}
