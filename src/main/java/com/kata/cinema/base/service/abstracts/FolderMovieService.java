package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.models.FolderMovie;

import java.util.List;

public interface FolderMovieService {
    public List<FolderMovie> getFolderMovieListByUserId (Long userId);
    public FolderMovie getFolderMovieById (Long folderMovieId);
}
