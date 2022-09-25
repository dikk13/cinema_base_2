package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.models.Movie;

import java.util.List;

public interface MovieResponseDao {
    List<Movie> getMovieListByFolderMovieId(Long folderMovieId, String sortMovieFolder, Integer pageNumber, Integer itemsOnPage);
    List<Movie> getMovieListByFolderMovieId_postSorting (Long folderMovieId, String sortMovieFolder, Integer pageNumber, Integer itemsOnPage);
    String[] sorted (String sortingParameters, String folderMovieId);
    String chooseAgrFuncParams (String sortingParameters);
    String getIdQueue(List<Object[]> result);
}