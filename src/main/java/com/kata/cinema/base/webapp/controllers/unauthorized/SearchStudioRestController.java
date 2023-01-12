package com.kata.cinema.base.webapp.controllers.unauthorized;


import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.response.ProductionMovieStudioResponseDto;
import com.kata.cinema.base.models.enums.ProductionSort;
import com.kata.cinema.base.service.dto.ProductionMovieStudioResponseDtoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/search/studios")
public class SearchStudioRestController {
    private final ProductionMovieStudioResponseDtoService productionMovieStudioResponseDtoService;

    public SearchStudioRestController(ProductionMovieStudioResponseDtoService productionMovieStudioResponseDtoService) {
        this.productionMovieStudioResponseDtoService = productionMovieStudioResponseDtoService;
    }

    @GetMapping("/page/{pageNumber}")
    public ResponseEntity<PageDto<ProductionMovieStudioResponseDto>> getPageDto(
            @PathVariable("pageNumber") Integer pageNumber,
            @RequestParam("itemsOnPage") Integer itemsOnPage,
            @RequestParam("filterPattern") String filterPattern,
            @RequestParam("productionSort") ProductionSort productionSort) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("filterPattern", filterPattern);
        params.put("productionSort", productionSort);
        return new ResponseEntity<>(productionMovieStudioResponseDtoService.getPageDtoWithParameters(pageNumber,itemsOnPage, params),
                HttpStatus.OK);
    }
}
