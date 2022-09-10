package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.models.FolderMovie;

import java.util.List;

public interface FolderMovieDao <PK, E> extends AbstractDao <PK, E> {
    public List <E> getFolderMovieListByUserId (PK UserId);
    public E getFolderMovieById (PK folderMovieId);
}
