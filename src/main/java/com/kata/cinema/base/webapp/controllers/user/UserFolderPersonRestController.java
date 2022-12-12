package com.kata.cinema.base.webapp.controllers.user;

import com.kata.cinema.base.dto.request.FolderRequestDto;
import com.kata.cinema.base.dto.response.FolderPersonResponseDto;
import com.kata.cinema.base.mappers.FolderPersonResponseDtoMapper;
import com.kata.cinema.base.mappers.FolderRequestDtoMapper;
import com.kata.cinema.base.models.FolderPerson;
import com.kata.cinema.base.service.dto.FolderPersonResponseDtoService;
import com.kata.cinema.base.service.entity.FolderPersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/folders")
@AllArgsConstructor
public class UserFolderPersonRestController {
    private final FolderPersonService folderPersonService;
    private final FolderRequestDtoMapper folderRequestDtoMapper;
    private final FolderPersonResponseDtoMapper folderPersonResponseDtoMapper;


    @DeleteMapping("/{id}/persons")
    public ResponseEntity<Void> deleteFolderPersonsById(@PathVariable Long id) {
        Optional<FolderPerson> folderPersonToDelete = folderPersonService.getById(id);
        if (folderPersonToDelete.isEmpty()) {
            throw new RuntimeException("Неверно передан id, пользователя с таким id нету ");
        }
        FolderPerson folderPerson = folderPersonToDelete.get();
        folderPerson.getFavourites().equals(false);
        folderPersonService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/persons")
    public ResponseEntity<List<FolderPersonResponseDto>> getFolderPersonResponseDtoList() {
        return ResponseEntity.ok(folderPersonResponseDtoMapper.toDTOList(folderPersonService.getAll()));
    }

    @PostMapping("/persons")
    public ResponseEntity<Void> addFolderPerson(@RequestBody FolderRequestDto folderRequestDto) {
        FolderPerson folderPerson = folderRequestDtoMapper.toFolderPerson(folderRequestDto);
        folderPerson.setFavourites(Boolean.FALSE);
        folderPerson.setPrivacy("PUBLIC");
        folderPersonService.create(folderPerson);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

