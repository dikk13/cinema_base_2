package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.ScoreMovieDao;
import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.response.ScoreMovieResponseDto;
import com.kata.cinema.base.service.dto.impl.PaginationDtoServiceImpl;
import com.kata.cinema.base.service.entity.ScoreMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScoreMovieServiceImpl extends PaginationDtoServiceImpl<ScoreMovieResponseDto> implements ScoreMovieService{

    private final ScoreMovieDao scoreMovieDao;

    @Autowired
    public ScoreMovieServiceImpl(ScoreMovieDao scoreMovieDao) {
        super(scoreMovieDao);
        this.scoreMovieDao = scoreMovieDao;
    }

    @Override
    public PageDto<ScoreMovieResponseDto> getPageDtoWithParameters
            (Integer currentPage, Integer itemsOnPage, Map<String, Object> parameters) {
        PageDto <ScoreMovieResponseDto> pageDto = new PageDto<>();
        List <ScoreMovieResponseDto> scoreMovieResponseDtoList = scoreMovieDao.getItemsDto(currentPage,itemsOnPage,parameters);
        pageDto.setCount(scoreMovieDao.getResultTotal(parameters));
        pageDto.setEntities(scoreMovieResponseDtoList);
        return pageDto;
    }
}
