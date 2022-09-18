package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.MovieResponseDto;
import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.models.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;


@Mapper(componentModel = "spring")
public interface MovieResponseDtoMapper {

    @Named("createGenresString")
    static String createGenresString (List<Genre> genres){
        StringBuilder stringBuilder = new StringBuilder();
        for (Genre genre: genres) {
            stringBuilder.append(genre.getName()).append(", ");
        }
        return stringBuilder.toString();
    }
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "originalName", target = "originalName")
    @Mapping(source = "dateRelease", target = "dateRelease")
    @Mapping(expression = "java(movie.timeToInt(movie.getTime()))", target = "time")
    @Mapping(source = "countries", target = "countries")
    @Mapping(source = "genres", target = "genres", qualifiedByName = "createGenresString")
    MovieResponseDto mapMovieToDto (Movie movie);
    List<MovieResponseDto> mapListOfMoviesToDto (List<Movie> movies);

}