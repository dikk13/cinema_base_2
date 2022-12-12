package com.kata.cinema.base.dao.dto;

import com.kata.cinema.base.dto.response.FolderPersonResponseDto;
import com.kata.cinema.base.dto.response.FolderResponseDto;

import java.util.List;

public interface FolderPersonResponseDtoDao {

    List<FolderPersonResponseDto> getFolderPersonResponseDtoList(Long id);
}
