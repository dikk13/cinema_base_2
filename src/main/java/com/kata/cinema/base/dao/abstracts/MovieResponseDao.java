package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.models.Movie;

import java.util.List;

public interface MovieResponseDao {
    List<Movie> getMovieListByFolderMovieId(Long folderMovieId, String sortMovieFolder, Integer pageNumber, Integer itemsOnPage);
    String sorted (String sortingParameters);
    String getIdrow (List<Object[]> result);
}