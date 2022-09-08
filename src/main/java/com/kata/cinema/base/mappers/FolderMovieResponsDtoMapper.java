package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.FolderMovieResponsDto;
import com.kata.cinema.base.models.FolderMovie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;


@Mapper
public interface FolderMovieResponsDtoMapper {
    FolderMovieResponsDtoMapper instance = Mappers.getMapper(FolderMovieResponsDtoMapper.class);
    @Mapping(source = "id", target = "id")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "privacy", target = "privacy")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")

    FolderMovieResponsDto mapSingleMovieFolderToDto (FolderMovie folderMovie);
    List<FolderMovieResponsDto> mapMovieFoldersListToDto (List<FolderMovie> folderMovieSet);
}
