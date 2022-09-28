package com.kata.cinema.base.webapp.controllers.movieRest;


import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.ReviewResponseDto;
import com.kata.cinema.base.enums.ReviewSortType;
import com.kata.cinema.base.enums.TypeReview;
import com.kata.cinema.base.service.abstracts.ReviewResponseDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class MovieRestController {


    private final ReviewResponseDtoService responseDtoService;

    @Autowired
    public MovieRestController(ReviewResponseDtoService responseDtoService) {
        this.responseDtoService = responseDtoService;
    }
// запрос GET /api/movies/{id}/reviews/page/{pageNumber}?itemsOnPage={itemsOnPage}&typeReview={typeReview}&reviewSortType={reviewSortType}

// ну я хз уже
    @GetMapping("/api/movies/{id}/reviews/page/{pageNumber}")
    public PageDto<ReviewResponseDto> getReview(
            @PathVariable("id") Integer movieId,                                                                      // айди фильма  reviewSortType,responseDtoService.getReview()
            @PathVariable("pageNumber") Integer pageNumber,                                                            //айди страницы
            @RequestParam(value = "itemsOnPage",required = false,defaultValue = "10") Integer itemsOnPage,                                                 //кол-во элементов на странице
            @RequestParam(value = "typeReview", required = false) TypeReview typeReview,                                              // тип рецензии
            @RequestParam(value = "reviewSortType", required = false, defaultValue = "DATE_ASC") ReviewSortType reviewSortType         // сортировка по типу
    ) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(String.valueOf(movieId),typeReview);
        PageDto<ReviewResponseDto> reviewResponseDtoPageDto = responseDtoService.getPageDtoWithParameters(pageNumber,itemsOnPage, parameters);
        return reviewResponseDtoPageDto;
    }
}
