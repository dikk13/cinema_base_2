package com.kata.cinema.base.webapp.controllers;


import com.kata.cinema.base.dto.FolderMovieResponsDto;
import com.kata.cinema.base.dto.MovieResponseDto;
import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.service.abstracts.FolderMovieResponsDtoService;
import com.kata.cinema.base.service.abstracts.MovieResponseDtoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/folders")
public class UserFolderMovieRestController {

    private final FolderMovieResponsDtoService folderMovieResponsDtoService;
    private final MovieResponseDtoService movieResponseDtoService;

    public UserFolderMovieRestController(FolderMovieResponsDtoService folderMovieResponsDtoService, MovieResponseDtoService movieResponseDtoService) {
        this.folderMovieResponsDtoService = folderMovieResponsDtoService;
        this.movieResponseDtoService = movieResponseDtoService;
    }


    @GetMapping("/movies")
    public ResponseEntity<List<FolderMovieResponsDto>> getFolderMovieResponsDtoListByUserId (@RequestParam(value = "userId") Long userId) {
        return new ResponseEntity<>(folderMovieResponsDtoService.getFolderMovieResponsDtoListByUserId(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/movies")
    public ResponseEntity<FolderMovieResponsDto> getFolderMovieResponsDtoById (@PathVariable("id") Long id) {
        return new ResponseEntity<>(folderMovieResponsDtoService.getFolderMovieResponsDtoById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/movies/page/{pageNumber}")
    public ResponseEntity<PageDto<MovieResponseDto>> MyNewFunc (
            @PathVariable("id") Long id,
            @PathVariable("pageNumber") Integer pageNumber,
            @RequestParam(value = "itemsOnPage", defaultValue = "10") Integer itemsOnPage,
            @RequestParam(value = "sortMovieFolder", defaultValue = "ORDER") String sortMovieFolder,
            @RequestParam(value = "showType", defaultValue = "ALL") String showType) {


        /* --- Будет возвращать list<MovieResponseDTO> из нескольких страниц  --- */

        List<MovieResponseDto> answer = movieResponseDtoService.getMovieResponseDtoListByFolderMovieId(id, sortMovieFolder, pageNumber, itemsOnPage);
        System.out.println("***********");
        System.out.println(answer);
        PageDto<MovieResponseDto> pageDto = new PageDto<>();
        pageDto.setCount(5L);
        pageDto.setEntities(answer);
        return new ResponseEntity<>(pageDto, HttpStatus.OK);
    }
}
