package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dto.response.ChapterResponseDto;
import com.kata.cinema.base.mappers.ChapterMapper;
import com.kata.cinema.base.service.dto.ChapterResponseDtoService;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.ChapterService;

import java.util.List;

public class ChapterResponseDtoServiceImpl extends AbstractServiceImpl<Long, ChapterResponseDto> implements ChapterResponseDtoService {

    private final ChapterService chapterService;
    private final ChapterMapper chapterMapper;

    protected ChapterResponseDtoServiceImpl(AbstractDao<Long, ChapterResponseDto> abstractDao, ChapterService chapterService, ChapterMapper chapterMapper) {
        super(abstractDao);
        this.chapterService = chapterService;
        this.chapterMapper = chapterMapper;
    }

    @Override
    public List<ChapterResponseDto> getChapterResponseDtoList() {
        return chapterMapper.toChapterResponseDtoList(chapterService.getAll());
    }
}
