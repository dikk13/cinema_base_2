package com.kata.cinema.base.mappers;

import com.kata.cinema.base.dto.response.FolderPersonResponseDto;
import com.kata.cinema.base.models.FolderPerson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface FolderPersonResponseDtoMapper {


    @Mapping(source = "user.id", target = "countPerson")
    FolderPersonResponseDto toDTO(FolderPerson folderPerson);

    List<FolderPersonResponseDto> toDTOList(List<FolderPerson> folderPerson);

    FolderPerson toFolderPerson(FolderPersonResponseDto folderPersonRequestDto);
}
