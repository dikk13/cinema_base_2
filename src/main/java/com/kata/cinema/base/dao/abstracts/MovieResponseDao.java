package com.kata.cinema.base.dao.abstracts;

import com.kata.cinema.base.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieResponseDao {
    List<Movie> getMovieListByFolderMovieId(Long folderMovieId);
}