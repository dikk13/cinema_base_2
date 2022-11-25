package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dto.response.ChapterResponseDto;
import com.kata.cinema.base.mappers.ChapterMapper;
import com.kata.cinema.base.service.dto.ChapterResponseDtoService;
import com.kata.cinema.base.service.entity.ChapterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterResponseDtoServiceImpl implements ChapterResponseDtoService {

    private final ChapterService chapterService;
    private final ChapterMapper chapterMapper;

    protected ChapterResponseDtoServiceImpl(ChapterService chapterService, ChapterMapper chapterMapper) {
        this.chapterService = chapterService;
        this.chapterMapper = chapterMapper;
    }

    @Override
    public List<ChapterResponseDto> getChapterResponseDtoList() {
        return chapterMapper.toChapterResponseDtoList(chapterService.getAll());
    }
}
