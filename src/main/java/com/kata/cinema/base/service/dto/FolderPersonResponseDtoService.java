package com.kata.cinema.base.service.dto;


import com.kata.cinema.base.dto.response.FolderPersonResponseDto;

import java.util.List;


public interface FolderPersonResponseDtoService {

    List<FolderPersonResponseDto> getFolderPersonResponseDtoList(Long userId);

}
