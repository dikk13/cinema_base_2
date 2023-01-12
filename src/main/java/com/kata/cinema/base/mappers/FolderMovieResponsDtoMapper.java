package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.response.FolderMovieResponsDto;
import com.kata.cinema.base.models.FolderMovie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FolderMovieResponsDtoMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "privacy", target = "privacy")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    FolderMovieResponsDto mapSingleMovieFolderToDto(FolderMovie folderMovie);

    List<FolderMovieResponsDto> mapMovieFoldersListToDto(List<FolderMovie> folderMovieSet);

    FolderMovie toFolder(FolderMovieResponsDto folderMovieResponsDto);
}
