package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.FolderRequestDto;
import com.kata.cinema.base.dto.response.FolderResponseDto;
import com.kata.cinema.base.models.FolderMovie;
import com.kata.cinema.base.models.FolderPerson;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface FolderResponseDtoMapper {
    FolderMovie toFolderMovie(FolderResponseDto folderResponseDto);

    FolderPerson toFolderPerson(FolderResponseDto folderResponseDto);

    List<FolderResponseDto> toDtoListFolderMovie(List<FolderMovie> folderMovies);

    List<FolderResponseDto> toDtoListFolderPerson(List<FolderPerson> folderPerson);

    FolderResponseDto toFolderResponseDtoFolderMovie(FolderMovie folderMovie);

    FolderResponseDto toFolderResponseDtoFolderPerson(FolderPerson folderPerson);

}