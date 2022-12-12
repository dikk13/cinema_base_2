package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.FolderPersonResponseDtoDao;
import com.kata.cinema.base.dto.response.FolderPersonResponseDto;
import com.kata.cinema.base.dto.response.FolderResponseDto;
import com.kata.cinema.base.mappers.FolderPersonResponseDtoMapper;
import com.kata.cinema.base.models.FolderPerson;
import com.kata.cinema.base.service.dto.FolderPersonResponseDtoService;
import com.kata.cinema.base.service.entity.FolderPersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderPersonResponseDtoServiceImpl implements FolderPersonResponseDtoService {

    private final FolderPersonService folderPersonService;
    private final FolderPersonResponseDtoMapper folderPersonResponseDtoMapper;

    public FolderPersonResponseDtoServiceImpl(FolderPersonService folderPersonService, FolderPersonResponseDtoMapper folderPersonResponseDtoMapper) {
        this.folderPersonService = folderPersonService;
        this.folderPersonResponseDtoMapper = folderPersonResponseDtoMapper;

    }

    @Override
    public List<FolderPersonResponseDto> getFolderPersonResponseDtoList(Long id) {
        List<FolderPerson> folderPersonList = folderPersonService.getFolderPersonById(id);
        return folderPersonResponseDtoMapper.toDTOList(folderPersonList);
    }
}