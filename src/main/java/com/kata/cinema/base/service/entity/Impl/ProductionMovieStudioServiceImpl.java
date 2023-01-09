package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.ProductionMovieStudioDao;
import com.kata.cinema.base.exception.MovieIdNotFoundException;
import com.kata.cinema.base.exception.ProductionStudioMovieNotFoundException;
import com.kata.cinema.base.exception.ProductionStudioNotFoundException;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.ProductionStudio;
import com.kata.cinema.base.models.ProductionStudioMovie;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.service.entity.ProductionMovieStudioService;
import com.kata.cinema.base.service.entity.ProductionStudioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductionMovieStudioServiceImpl extends AbstractServiceImpl<Long, ProductionStudioMovie>
        implements ProductionMovieStudioService {

    private final ProductionMovieStudioDao productionMovieStudioDao;
    private final MovieService movieService;
    private final ProductionStudioService productionStudioService;

    public ProductionMovieStudioServiceImpl(ProductionMovieStudioDao productionMovieStudioDao,
                                            MovieService movieService,
                                            ProductionStudioService productionStudioService) {
        super(productionMovieStudioDao);
        this.productionMovieStudioDao = productionMovieStudioDao;
        this.movieService = movieService;
        this.productionStudioService = productionStudioService;
    }

    @Override
    public Optional<ProductionStudioMovie> getStudioByMovieId(Long id) {
        return productionMovieStudioDao.getStudioByMovieId(id);
    }
    @Override
    @Transactional
    public void addStudioMovieConnection(Long movieId, Long studioId) {
        Optional<Movie> movieOptional = movieService.getById(movieId);
        if (movieOptional.isEmpty()) {
            throw new MovieIdNotFoundException(String.format("Movie with id = %d is not found", movieId));
        }
        Optional<ProductionStudio> productionStudioOptional = productionStudioService.getById(studioId);
        if (productionStudioOptional.isEmpty()) {
            throw new ProductionStudioNotFoundException(String.format("ProductionStudio with id = %d is not found", studioId));
        }
        ProductionStudioMovie productionStudioMovie = new ProductionStudioMovie();
        productionStudioMovie.setMovie(movieOptional.get());
        productionStudioMovie.setStudio(productionStudioOptional.get());
        productionMovieStudioDao.create(productionStudioMovie);
    }

    @Override
    @Transactional
    public void deleteStudioMovieConnection(Long movieId, Long studioId) {
        Optional<Movie> movieOptional = movieService.getById(movieId);
        if (movieOptional.isEmpty()) {
            throw new MovieIdNotFoundException(String.format("Movie with id = %d is not found", movieId));
        }
        Optional<ProductionStudio> productionStudioOptional = productionStudioService.getById(studioId);
        if (productionStudioOptional.isEmpty()) {
            throw new ProductionStudioNotFoundException(String.format("ProductionStudio with id = %d is not found", studioId));
        }
        Optional<ProductionStudioMovie> productionStudioMovieOptional =
                productionMovieStudioDao.getByMovieIdStudioId(movieId, studioId);
        if (productionStudioMovieOptional.isEmpty()) {
            throw new ProductionStudioMovieNotFoundException(
                    String.format("Connection between movie (id = %d) and studio (id = %d) is not found", movieId, studioId));
        }
        productionMovieStudioDao.delete(productionStudioMovieOptional.get());
    }

}
