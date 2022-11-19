package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.dto.response.ProductionMovieStudioResponseDto;
import com.kata.cinema.base.mappers.ProductionStudioMovieMapper;
import com.kata.cinema.base.service.entity.ProductionMovieStudioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;

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
    public ResponseEntity<ProductionMovieStudioResponseDto> getStudioMovie(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productionStudioMovieMapper.productionStudioMovieToProductionMovieStudioResponseDto(
                productionMovieStudioService.getStudioByMovieId(id).orElseThrow(() -> new NoResultException("No entity found for query"))));
    }
}
