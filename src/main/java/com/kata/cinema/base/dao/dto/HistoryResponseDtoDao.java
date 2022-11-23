package com.kata.cinema.base.dao.dto;

import com.kata.cinema.base.dto.response.HistoryResponseDto;

import java.util.List;

public interface HistoryResponseDtoDao {
    List<HistoryResponseDto> getHistoryResponseDtoListByUserId(Long userId);
}
