package com.kata.cinema.base.service.dto;

import com.kata.cinema.base.dto.response.HistoryResponseDto;

import java.util.List;

public interface HistoryResponseDtoService {
    List<HistoryResponseDto> getHistoryResponseDtoListByUserId(Long userId);
}
