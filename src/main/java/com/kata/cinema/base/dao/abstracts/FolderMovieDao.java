package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.models.FolderMovie;

import java.util.List;

public interface FolderMovieDao {
    public List <FolderMovie> getFolderMovieListByUserId (Long UserId);
    public FolderMovie getFolderMovieById (Long folderMovieId);
}
