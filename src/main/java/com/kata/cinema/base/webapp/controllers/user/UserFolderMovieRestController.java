package com.kata.cinema.base.webapp.controllers.user;


import com.kata.cinema.base.dto.FolderMovieResponsDto;
import com.kata.cinema.base.service.abstracts.FolderMovieResponsDtoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<FolderMovieResponsDto>> getFolderMovieResponsDtoListByUserId (@RequestParam(value = "userId") Long userId) {
        return new ResponseEntity<>(folderMovieResponsDtoService.getFolderMovieResponsDtoListByUserId(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FolderMovieResponsDto> getFolderMovieResponsDtoById (@PathVariable("id") Long id) {
        System.out.println("GET TEST");
        return new ResponseEntity<>(folderMovieResponsDtoService.getFolderMovieResponsDtoById(id), HttpStatus.OK);
    }

}
