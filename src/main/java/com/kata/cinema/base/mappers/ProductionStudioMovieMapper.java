package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.response.ProductionMovieStudioResponseDto;
import com.kata.cinema.base.models.ProductionStudioMovie;
import org.mapstruct.*;
import org.springframework.stereotype.Component;


@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductionStudioMovieMapper {
    ProductionStudioMovie productionMovieStudioResponseDtoToProductionStudioMovie(ProductionMovieStudioResponseDto productionMovieStudioResponseDto);

    @Mapping(source = "studio.name", target = "name")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "performance", target = "performance")
    ProductionMovieStudioResponseDto productionStudioMovieToProductionMovieStudioResponseDto(ProductionStudioMovie productionStudioMovie);

}
