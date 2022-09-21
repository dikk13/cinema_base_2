package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.MovieResponseDto;
import com.kata.cinema.base.models.Genre;
import com.kata.cinema.base.models.Movie;
import com.kata.cinema.base.models.MoviePerson;
import com.kata.cinema.base.models.enums.CharacterType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;


@Mapper(componentModel = "spring")
public interface MovieResponseDtoMapper {

    @Named("createGenresString")
    static String createGenresString (Set<Genre> genres){
        int counter = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (Genre genre: genres) {
            stringBuilder.append(genre.getName());
            if (counter < genres.size() - 1) {
                stringBuilder.append(", ");
                counter++;
            }
        }
        return stringBuilder.toString();
    }

    @Named("createDirectorsString")
    static String createDirectorsString (Set<MoviePerson> moviePersonSet) {
        int counter = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (MoviePerson moviePerson: moviePersonSet) {
            if (Integer.parseInt(moviePerson.getTypeCharacter()) == CharacterType.NO_CHARACTER_MOVIE.ordinal()) {
                if (counter > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(moviePerson.getPerson().getFirstName()).append(" ").append(moviePerson.getPerson().getLastName());
                counter++;
            }
        }
        return stringBuilder.toString();
    }

    @Named("createRolesString")
    static String createRolesString (Set<MoviePerson> moviePersonSet) {
        int counter = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (MoviePerson moviePerson: moviePersonSet) {
            if (Integer.parseInt(moviePerson.getTypeCharacter()) == CharacterType.MAIN_CHARACTER.ordinal()) {
                if (counter > 0) {
                    stringBuilder.append(", ");
                }
                    stringBuilder.append(moviePerson.getPerson().getFirstName()).append(" ").append(moviePerson.getPerson().getLastName());
                    counter++;
            }
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
    @Mapping(source = "moviePerson", target = "directors", qualifiedByName = "createDirectorsString")
    @Mapping(source = "moviePerson", target = "roles", qualifiedByName = "createRolesString")
    MovieResponseDto mapMovieToDto (Movie movie);
    List<MovieResponseDto> mapListOfMoviesToDto (List<Movie> movies);

}