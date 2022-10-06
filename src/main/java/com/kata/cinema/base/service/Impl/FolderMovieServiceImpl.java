package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.AbstractDao;
import com.kata.cinema.base.dao.abstracts.FolderMovieDao;
import com.kata.cinema.base.models.FolderMovie;
import com.kata.cinema.base.service.abstracts.FolderMovieService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FolderMovieServiceImpl extends AbstractServiceImpl<Long, FolderMovie> implements  FolderMovieService{

    private final FolderMovieDao folderMovieDao;

    protected FolderMovieServiceImpl(AbstractDao<Long, FolderMovie> abstractDao, FolderMovieDao folderMovieDao) {
        super(abstractDao);
        this.folderMovieDao = folderMovieDao;
    }


    @Override
    public List<FolderMovie> getFolderMovieListByUserId(Long userId) {
        return folderMovieDao.getFolderMovieListByUserId(userId);
    }

    @Override
    public FolderMovie getFolderMovieById(Long folderMovieId) {
        return folderMovieDao.getFolderMovieById(folderMovieId);
    }
}
