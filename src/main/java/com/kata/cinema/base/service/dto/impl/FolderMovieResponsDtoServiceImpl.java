package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.FolderMovieResponsDtoDao;
import com.kata.cinema.base.dto.response.FolderMovieResponsDto;
import com.kata.cinema.base.dto.response.FolderResponseDto;
import com.kata.cinema.base.mappers.FolderMovieResponsDtoMapper;
import com.kata.cinema.base.models.FolderMovie;
import com.kata.cinema.base.service.dto.FolderMovieResponsDtoService;
import com.kata.cinema.base.service.entity.FolderMovieService;
import org.springframework.stereotype.Service;

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
        return folderMovieResponsDtoMapper.mapMovieFoldersListToDto(folderMovieList);
    }

    @Override
    public FolderMovieResponsDto getFolderMovieResponsDtoById(Long folderMovieId) {
        return folderMovieResponsDtoDao.getFolderMovieResponsDtoById(folderMovieId);
    }

    @Override
    public List<FolderResponseDto> getFolderByUser(Long userId) {
        return folderMovieResponsDtoDao.getFolderByUser(userId);
    }
}