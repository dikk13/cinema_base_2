package com.kata.cinema.base.webapp.controllers.user;

import com.kata.cinema.base.dto.request.FolderRequestDto;
import com.kata.cinema.base.dto.response.FolderPersonResponseDto;
import com.kata.cinema.base.exception.FolderPersonIdNotFoundException;
import com.kata.cinema.base.mappers.FolderPersonResponseDtoMapper;
import com.kata.cinema.base.mappers.FolderRequestDtoMapper;
import com.kata.cinema.base.models.FolderPerson;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.models.enums.Privacy;
import com.kata.cinema.base.service.dto.FolderMovieResponsDtoService;
import com.kata.cinema.base.service.dto.FolderPersonResponseDtoService;
import com.kata.cinema.base.service.entity.FolderPersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/folders")
@AllArgsConstructor
public class UserFolderPersonRestController {
    private final FolderPersonService folderPersonService;
    private final FolderRequestDtoMapper folderRequestDtoMapper;
    private final FolderPersonResponseDtoService folderPersonResponseDtoService;


    @DeleteMapping("/{id}/persons")
    public ResponseEntity<Void> deleteFolderPersonsById(@PathVariable Long id) {
        Optional<FolderPerson> folderPersonToDelete = folderPersonService.getById(id);
        if (folderPersonToDelete.isEmpty()) {
            throw new FolderPersonIdNotFoundException("Неверно передан id, пользователя с таким id нету ");
        }
        FolderPerson folderPerson = folderPersonToDelete.get();
        folderPerson.getFavourites().equals(false);
        folderPersonService.deleteById(id);

        if (folderPerson.getFavourites() != false) {
            throw new RuntimeException("Ошибка!");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/persons")
    public ResponseEntity<List<FolderPersonResponseDto>> getFolderPersonResponseDtoListByUserId(@AuthenticationPrincipal User user) {
        User userActive = (User) ((Authentication) user).getPrincipal();
        return new ResponseEntity<>(folderPersonResponseDtoService.getFolderPersonResponseDtoList(userActive.getId()), HttpStatus.OK);
    }

    @PostMapping("/persons")
    public ResponseEntity<Void> addFolderPerson(@RequestBody FolderRequestDto folderRequestDto) {
        FolderPerson folderPerson = folderRequestDtoMapper.toFolderPerson(folderRequestDto);
        folderPerson.setFavourites(Boolean.FALSE);
        folderPerson.setPrivacy(Privacy.PUBLIC);
        folderPersonService.create(folderPerson);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}/persons")
    public ResponseEntity<Void> updateFolderPersonPrivacy(
            @RequestParam(name = "privacy") String privacy, @PathVariable("id") Long id) {
        Optional<FolderPerson> folderPersonToUpdatePrivacy = folderPersonService.getById(id);
        if (folderPersonToUpdatePrivacy.isEmpty()) {
            throw new FolderPersonIdNotFoundException("FolderPerson with id is not found");
        }
        FolderPerson folderPerson = folderPersonToUpdatePrivacy.get();
        folderPerson.setPrivacy(Privacy.valueOf(privacy));
        folderPersonService.update(folderPerson);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/persons")
    public ResponseEntity<Void> updateFolderPersonNameDescription(
            @PathVariable("id") Long id, @RequestBody FolderRequestDto folderRequestDto) {
            Optional<FolderPerson> folderPersonToUpdate = folderPersonService.getById(id);
        if (folderPersonToUpdate.isEmpty()) {
            throw new FolderPersonIdNotFoundException("FolderPerson with id is not found");
        }
        FolderPerson folderPerson = folderPersonToUpdate.get();
        folderPerson.setName(folderRequestDto.getName());
        folderPerson.setDescription(folderRequestDto.getDescription());
        folderPersonService.update(folderPerson);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

