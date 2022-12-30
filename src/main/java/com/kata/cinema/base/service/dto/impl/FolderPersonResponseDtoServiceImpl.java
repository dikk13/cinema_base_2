package com.kata.cinema.base.service.dto.impl;


import com.kata.cinema.base.dao.dto.FolderPersonResponseDtoDao;
import com.kata.cinema.base.dto.response.FolderPersonResponseDto;
import com.kata.cinema.base.service.dto.FolderPersonResponseDtoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderPersonResponseDtoServiceImpl implements FolderPersonResponseDtoService {

    private final FolderPersonResponseDtoDao folderPersonResponseDtoDao;

    public FolderPersonResponseDtoServiceImpl(FolderPersonResponseDtoDao folderPersonResponseDtoDao) {
        this.folderPersonResponseDtoDao = folderPersonResponseDtoDao;
    }

    @Override
    public List<FolderPersonResponseDto> getFolderPersonResponseDtoList(Long userId) {
        return folderPersonResponseDtoDao.getFolderPersonResponseDtoList(userId);
    }
}