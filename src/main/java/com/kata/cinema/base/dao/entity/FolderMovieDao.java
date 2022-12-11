package com.kata.cinema.base.dao.entity;

import com.kata.cinema.base.models.FolderMovie;
import com.kata.cinema.base.models.enums.Category;

import java.util.List;
import java.util.Optional;

public interface FolderMovieDao extends AbstractDao <Long, FolderMovie> {
    List <FolderMovie> getFolderMovieListByUserId (Long userId);
    Optional<FolderMovie> getFolderMovieById (Long folderMovieId);

}
