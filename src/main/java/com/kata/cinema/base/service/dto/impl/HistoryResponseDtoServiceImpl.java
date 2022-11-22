package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.HistoryResponseDtoDao;
import com.kata.cinema.base.dto.response.HistoryResponseDto;
import com.kata.cinema.base.service.dto.HistoryResponseDtoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryResponseDtoServiceImpl implements HistoryResponseDtoService {

    private final HistoryResponseDtoDao historyResponseDtoDao;

    public HistoryResponseDtoServiceImpl(HistoryResponseDtoDao historyResponseDtoDao) {
        this.historyResponseDtoDao = historyResponseDtoDao;
    }

    @Override
    public List<HistoryResponseDto> getHistoryResponseDtoListByUserId(Long userId) {
        return historyResponseDtoDao.getHistoryResponseDtoListByUserId(userId);
    }
}
