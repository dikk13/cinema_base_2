package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dao.entity.FolderMovieDao;
import com.kata.cinema.base.models.FolderMovie;
import com.kata.cinema.base.models.enums.Category;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.FolderMovieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FolderMovieServiceImpl extends AbstractServiceImpl<Long, FolderMovie> implements FolderMovieService {

    private final FolderMovieDao folderMovieDao;

    protected FolderMovieServiceImpl(FolderMovieDao folderMovieDao) {
        super(folderMovieDao);
        this.folderMovieDao = folderMovieDao;
    }


    @Override
    public List<FolderMovie> getFolderMovieListByUserId(Long userId) {
        return folderMovieDao.getFolderMovieListByUserId(userId);
    }

    @Override
    public Optional<FolderMovie> getFolderMovieById(Long folderMovieId) {
        return folderMovieDao.getFolderMovieById(folderMovieId);
    }

}
