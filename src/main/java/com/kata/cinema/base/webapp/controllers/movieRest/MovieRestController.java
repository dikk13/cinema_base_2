package com.kata.cinema.base.webapp.controllers.movieRest;


import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.ReviewResponseDto;
import com.kata.cinema.base.service.abstracts.ReviewResponseDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MovieRestController {


    private final ReviewResponseDtoService responseDtoService;

    @Autowired
    public MovieRestController(ReviewResponseDtoService responseDtoService) {
        this.responseDtoService = responseDtoService;
    }

    @GetMapping("/api/movies/{id}/reviews/page/{pageNumber}")
    public PageDto<ReviewResponseDto> getReviewResponseDtoPageDto(@PathVariable("id") Long id, @PathVariable("pageNumber") Long pageNumber) {
        return responseDtoService.getReviewResponseDto();
    }
}
