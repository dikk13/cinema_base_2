package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.service.entity.ProductionMovieStudioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/movies/{movieId}/studio/{studioId}")
public class AdminProductionStudioMovieController {
    private final ProductionMovieStudioService productionMovieStudioService;

    public AdminProductionStudioMovieController(ProductionMovieStudioService productionMovieStudioService) {
        this.productionMovieStudioService = productionMovieStudioService;
    }

    @PostMapping
    public ResponseEntity<Void> addProductionStudioMovieConnection(@PathVariable("movieId") Long moviesId,
                                                                   @PathVariable("studioId") Long studioId) {
        productionMovieStudioService.addStudioMovieConnection(moviesId, studioId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProductionStudioMovieConnection(@PathVariable("movieId") Long moviesId,
                                                                      @PathVariable("studioId") Long studioId) {
        productionMovieStudioService.deleteStudioMovieConnection(moviesId, studioId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
