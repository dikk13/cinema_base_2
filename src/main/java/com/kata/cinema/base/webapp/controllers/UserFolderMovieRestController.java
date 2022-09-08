package com.kata.cinema.base.webapp.controllers;


import com.kata.cinema.base.dto.FolderMovieResponsDto;
import com.kata.cinema.base.service.abstracts.FolderMovieResponsDtoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/foldermovies")
public class UserFolderMovieRestController {

    private final FolderMovieResponsDtoService folderMovieResponsDtoService;

    public UserFolderMovieRestController(FolderMovieResponsDtoService folderMovieResponsDtoService) {
        this.folderMovieResponsDtoService = folderMovieResponsDtoService;
    }


    @GetMapping
    public List<FolderMovieResponsDto> getFolderMovieResponsDtoListByUserId (@RequestParam(value = "userId") Long userId) {
        return folderMovieResponsDtoService.getFolderMovieResponsDtoListByUserId(userId);
    }

    @GetMapping(value = "/{id}")
    public FolderMovieResponsDto getFolderMovieResponsDtoById (@PathVariable("id") Long id) {
        return folderMovieResponsDtoService.getFolderMovieResponsDtoById(id);
    }

}
