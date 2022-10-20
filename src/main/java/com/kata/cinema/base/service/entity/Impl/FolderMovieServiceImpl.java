package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dao.entity.FolderMovieDao;
import com.kata.cinema.base.models.FolderMovie;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.FolderMovieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FolderMovieServiceImpl extends AbstractServiceImpl<Long, FolderMovie> implements FolderMovieService {

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
