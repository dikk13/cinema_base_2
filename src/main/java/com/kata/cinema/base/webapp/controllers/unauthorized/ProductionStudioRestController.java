package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.dto.ProductionMovieStudioResponseDto;
import com.kata.cinema.base.mappers.ProductionStudioMovieMapper;
import com.kata.cinema.base.service.abstracts.ProductionMovieStudioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
public class ProductionStudioRestController {

    private final ProductionMovieStudioService productionMovieStudioService;

    private final ProductionStudioMovieMapper productionStudioMovieMapper;

    public ProductionStudioRestController(ProductionMovieStudioService productionMovieStudioService,
                                          ProductionStudioMovieMapper productionStudioMovieMapper) {
        this.productionMovieStudioService = productionMovieStudioService;
        this.productionStudioMovieMapper = productionStudioMovieMapper;
    }

    @GetMapping("/{id}/studios")
    public ProductionMovieStudioResponseDto getStudioMovie(@PathVariable("id") Long id) {
        return productionStudioMovieMapper.productionStudioMovieToProductionMovieStudioResponseDto(
                productionMovieStudioService.getStudioByMovieId(id));
    }

}
