package com.kata.cinema.base.webapp.controllers.user;

import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.request.FolderRequestDto;
import com.kata.cinema.base.dto.response.FolderMovieResponsDto;
import com.kata.cinema.base.dto.response.FolderResponseDto;
import com.kata.cinema.base.dto.response.MovieResponseDto;
import com.kata.cinema.base.exception.CategoryNotFoundException;
import com.kata.cinema.base.exception.FolderMovieIdNotFoundException;
import com.kata.cinema.base.mappers.FolderRequestDtoMapper;
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

import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/folders")
@AllArgsConstructor
public class UserFolderMovieRestController {

    private final FolderMovieResponsDtoService folderMovieResponsDtoService;
    private final MovieResponseDtoService movieResponseDtoService;

    private final FolderMovieService folderMovieService;
    private final FolderRequestDtoMapper folderRequestDtoMapper;


    @GetMapping("/movies")
    public ResponseEntity<List<FolderMovieResponsDto>> getFolderMovieResponseDtoListByUserId(@RequestParam(value = "userId") Long userId) {
        return new ResponseEntity<>(folderMovieResponsDtoService.getFolderMovieResponsDtoListByUserId(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/movies")
    public ResponseEntity<FolderMovieResponsDto> getFolderMovieResponsDtoById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(folderMovieResponsDtoService.getFolderMovieResponsDtoById(id)
                .orElseThrow(() -> new NoResultException("No entity found for query")), HttpStatus.OK);
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
            @RequestBody FolderRequestDto folderRequestDto) {
        FolderMovie folderMovie = folderRequestDtoMapper.toFolderMovie(folderRequestDto);
        folderMovie.setCategory(Category.valueOf("CUSTOM"));
        folderMovie.setPrivacy(Privacy.valueOf("PUBLIC"));
        folderMovieService.create(folderMovie);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/movies")
    public ResponseEntity<Void> deleteFolderMovieById(@PathVariable Long id) {
        Optional<FolderMovie> folderMovieToDelete = folderMovieService.getById(id);
        if (folderMovieToDelete.isEmpty()) {
            throw new FolderMovieIdNotFoundException("Неверно передан id, пользователя с таким id нету ");
        }
        FolderMovie folderMovie = folderMovieToDelete.get();
        folderMovie.getCategory().equals(Category.CUSTOM);
        folderMovieService.deleteById(id);

        if (folderMovie.getCategory() != Category.CUSTOM) {
            throw new CategoryNotFoundException("Категория неверна ");
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
