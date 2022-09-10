package com.kata.cinema.base.service.Impl;

import com.kata.cinema.base.dao.abstracts.FolderMovieResponsDtoDao;
import com.kata.cinema.base.dto.FolderMovieResponsDto;
import com.kata.cinema.base.mappers.FolderMovieResponsDtoMapper;
import com.kata.cinema.base.models.FolderMovie;
import com.kata.cinema.base.service.abstracts.FolderMovieResponsDtoService;
import com.kata.cinema.base.service.abstracts.FolderMovieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FolderMovieResponsDtoServiceImpl implements FolderMovieResponsDtoService {


    private final FolderMovieResponsDtoMapper folderMovieResponsDtoMapper;
    private final FolderMovieService folderMovieService;
    private final FolderMovieResponsDtoDao folderMovieResponsDtoDao;

    public FolderMovieResponsDtoServiceImpl(
            FolderMovieResponsDtoMapper folderMovieResponsDtoMapper,
            FolderMovieService folderMovieService,
            FolderMovieResponsDtoDao folderMovieResponsDtoDao) {
        this.folderMovieResponsDtoMapper = folderMovieResponsDtoMapper;
        this.folderMovieService = folderMovieService;
        this.folderMovieResponsDtoDao = folderMovieResponsDtoDao;
    }

    @Override
    public List<FolderMovieResponsDto> getFolderMovieResponsDtoListByUserId(Long userId) {
        List<FolderMovie> folderMovieList = folderMovieService.getFolderMovieListByUserId(userId);
//        return FolderMovieResponsDtoMapper.instance.mapMovieFoldersListToDto(folderMovieList);
        return folderMovieResponsDtoMapper.mapMovieFoldersListToDto(folderMovieList);
    }

    @Transactional
    @Override
    public FolderMovieResponsDto getFolderMovieResponsDtoById(Long folderMovieId) {
        return folderMovieResponsDtoDao.getFolderMovieResponsDtoById(folderMovieId);
    }
}
