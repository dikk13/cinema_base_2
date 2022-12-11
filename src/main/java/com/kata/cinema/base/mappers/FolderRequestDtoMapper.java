package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.request.FolderRequestDto;
import com.kata.cinema.base.models.FolderMovie;
import com.kata.cinema.base.models.FolderPerson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface FolderRequestDtoMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    FolderMovie toFolderMovie(FolderRequestDto folderRequestDto);
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    FolderPerson toFolderPerson (FolderRequestDto folderRequestDto);

    List<FolderRequestDto> toDtoListFolderMovie (List<FolderMovie>folderMovies);

    List<FolderRequestDto> toDtoListFolderPerson(List<FolderPerson>folderPerson);

    FolderRequestDto toFolderRequestDtoFolderMovie (FolderMovie folderMovie);

    FolderRequestDto toFolderRequestDtoFolderPerson (FolderPerson folderPerson);


}
