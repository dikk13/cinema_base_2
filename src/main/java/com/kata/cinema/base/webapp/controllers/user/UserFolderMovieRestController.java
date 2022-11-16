package com.kata.cinema.base.webapp.controllers.user;

import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.response.FolderMovieResponsDto;
import com.kata.cinema.base.dto.response.FolderResponseDto;
import com.kata.cinema.base.dto.response.MovieResponseDto;
import com.kata.cinema.base.mappers.FolderMovieResponsDtoMapper;
import com.kata.cinema.base.models.FolderMovie;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.models.enums.Category;
import com.kata.cinema.base.models.enums.Privacy;
import com.kata.cinema.base.models.enums.ShowType;
import com.kata.cinema.base.models.enums.SortMovieFolderType;
import com.kata.cinema.base.service.dto.FolderMovieResponsDtoService;
import com.kata.cinema.base.service.dto.MovieResponseDtoService;
import com.kata.cinema.base.service.entity.FolderMovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user/folders")
@AllArgsConstructor
public class UserFolderMovieRestController {

    private final FolderMovieResponsDtoService folderMovieResponsDtoService;
    private final MovieResponseDtoService movieResponseDtoService;
    private final FolderMovieResponsDtoMapper folderMovieResponsDtoMapper;
    private final FolderMovieService folderMovieService;

    @GetMapping("/movies")
    public ResponseEntity<List<FolderMovieResponsDto>> getFolderMovieResponsDtoListByUserId(@RequestParam(value = "userId") Long userId) {
        return new ResponseEntity<>(folderMovieResponsDtoService.getFolderMovieResponsDtoListByUserId(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/movies")
    public ResponseEntity<FolderMovieResponsDto> getFolderMovieResponsDtoById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(folderMovieResponsDtoService.getFolderMovieResponsDtoById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/movies/page/{pageNumber}")
    public ResponseEntity<PageDto<MovieResponseDto>> getMoviesDtoListFromFolderMovieById(
            @PathVariable("id") Long id,
            @PathVariable("pageNumber") Integer pageNumber,
            @RequestParam(value = "itemsOnPage", defaultValue = "10") Integer itemsOnPage,
            @RequestParam(value = "sortMovieFolder", required = false, defaultValue = "ORDER") SortMovieFolderType sortMovieFolderType,
            @RequestParam(value = "showType", required = false, defaultValue = "ALL") ShowType showType) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("folderMovieId", id);
        parameters.put("sortingType", sortMovieFolderType);
        parameters.put("showType", showType);
        PageDto<MovieResponseDto> answer = movieResponseDtoService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters);
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FolderResponseDto>> getFolderByUser(@AuthenticationPrincipal User user) {
        User activeUser = (User) ((Authentication) user).getPrincipal();
        return new ResponseEntity<>(folderMovieResponsDtoService.getFolderByUser(activeUser.getId()), HttpStatus.OK);
    }

    @PostMapping("/movies")
    public ResponseEntity<Void> createNewFolderByMovie(
            @RequestBody FolderMovieResponsDto folder,
            @RequestParam(value = "name", required = false, defaultValue = "Новая папка") String name) {
        FolderMovie folderMovie = folderMovieResponsDtoMapper.toFolder(folder);
        folderMovie.setName(name);
        folderMovie.setCategory(Category.valueOf("CUSTOM"));
        folderMovie.setPrivacy(Privacy.valueOf("PUBLIC"));
        folderMovieService.create(folderMovie);
        return ResponseEntity.ok(null);
    }
}