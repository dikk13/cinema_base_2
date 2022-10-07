package com.kata.cinema.base.service.abstracts;

import com.kata.cinema.base.models.FolderMovie;
import com.kata.cinema.base.service.Impl.AbstractServiceImpl;

import java.util.List;

public interface FolderMovieService extends AbstractService<Long, FolderMovie> {
    public List<FolderMovie> getFolderMovieListByUserId (Long userId);
    public FolderMovie getFolderMovieById (Long folderMovieId);
}
