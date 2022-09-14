package com.kata.cinema.base.webapp.controllers;


import com.kata.cinema.base.dto.FolderMovieResponsDto;
import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.service.abstracts.FolderMovieResponsDtoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/folders")
public class UserFolderMovieRestController {

    private final FolderMovieResponsDtoService folderMovieResponsDtoService;

    public UserFolderMovieRestController(FolderMovieResponsDtoService folderMovieResponsDtoService) {
        this.folderMovieResponsDtoService = folderMovieResponsDtoService;
    }


    @GetMapping("/movies")
    public ResponseEntity<List<FolderMovieResponsDto>> getFolderMovieResponsDtoListByUserId (@RequestParam(value = "userId") Long userId) {
        return new ResponseEntity<>(folderMovieResponsDtoService.getFolderMovieResponsDtoListByUserId(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/movies")
    public ResponseEntity<FolderMovieResponsDto> getFolderMovieResponsDtoById (@PathVariable("id") Long id) {
        System.out.println("GET TEST");
        return new ResponseEntity<>(folderMovieResponsDtoService.getFolderMovieResponsDtoById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/movies/page/{pageNumber}")
    public ResponseEntity<PageDto<Movie>> MyNewFunc (
            @PathVariable("id") Long id,
            @PathVariable("pageNumber") Integer pageNumber,
            @RequestParam(value = "itemsOnPage", defaultValue = "10") Integer itemsOnPage,
            @RequestParam(value = "sortMovieFolder", defaultValue = "ORDER") String sortMovieFolder,
            @RequestParam(value = "showType", defaultValue = "ALL") String showType) {
        System.out.println("it is my func & we have:");
        System.out.println("id = " + id);
        System.out.println("pageNumber = " + pageNumber);
        System.out.println("itemsOnPage = " + itemsOnPage);
        System.out.println("sortMovieFolder = " + sortMovieFolder);
        System.out.println("showType = " + showType);
        System.out.println("go try...");

        /* --- Будет возвращать list<MovieResponseDTO> из нескольких страниц  --- */

//        System.out.println("получаем фильмы с folder_id - 4");
//        movieResponseDtoService.getMovieListByFolderMovieId(4L);
//        System.out.println("получаем фильмы с folder_id - 1");
//        movieResponseDtoService.getMovieListByFolderMovieId(1L);
//        System.out.println("получаем фильмы с folder_id - 2");
//        movieResponseDtoService.getMovieListByFolderMovieId(2L);


        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}
