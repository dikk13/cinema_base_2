package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.models.FolderMovie;

import java.util.List;

public interface FolderMovieDao extends AbstractDao <Long, FolderMovie> {
    List <FolderMovie> getFolderMovieListByUserId (Long userId);
    FolderMovie getFolderMovieById (Long folderMovieId);
}
