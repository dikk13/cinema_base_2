package com.kata.cinema.base.webapp.controllers.user;


import com.kata.cinema.base.dto.FolderMovieResponsDto;
import com.kata.cinema.base.dto.MovieResponseDto;
import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.models.enums.ShowType;
import com.kata.cinema.base.models.enums.SortMovieFolderType;
import com.kata.cinema.base.service.abstracts.FolderMovieResponsDtoService;
import com.kata.cinema.base.service.abstracts.MovieResponseDtoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<PageDto<MovieResponseDto>> getMoviesDtoListFromFolderMovieById (
            @PathVariable("id") Long id,
            @PathVariable("pageNumber") Integer pageNumber,
            @RequestParam(value = "itemsOnPage", defaultValue = "10") Integer itemsOnPage,
            @RequestParam(value = "sortMovieFolder", required = false, defaultValue = "ORDER") SortMovieFolderType sortMovieFolderType,
            @RequestParam(value = "showType", required = false, defaultValue = "ALL") ShowType showType) {

        Map <String, Object> parameters = new HashMap<>();
        parameters.put("folderMovieId", id);
        parameters.put("sortingType", sortMovieFolderType);
        parameters.put("showType", showType);
        PageDto <MovieResponseDto> answer = movieResponseDtoService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }
}