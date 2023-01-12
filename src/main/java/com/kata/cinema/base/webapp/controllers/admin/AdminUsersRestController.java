package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.dto.request.UserRequestDto;
import com.kata.cinema.base.dto.response.UserResponseDto;
import com.kata.cinema.base.service.dto.UserDtoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUsersRestController {
    private final UserDtoService userDtoService;

    public AdminUsersRestController(UserDtoService userDtoService) {
        this.userDtoService = userDtoService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserResponseDto(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userDtoService.getUserResponseDtoById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") Long id,
                                           @RequestBody UserRequestDto userRequestDto) {
        userDtoService.updateUser(id,userRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserRequestDto userRequestDto) {
        userDtoService.create(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> disableUser(@PathVariable("id") Long id) {
        userDtoService.disableUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", params = {"roleId"})
    public ResponseEntity<Void> addRole(@PathVariable("id") Long id,
                                        @RequestParam("roleId") Long roleId) {
        userDtoService.addRole(id, roleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", params = {"roleId"})
    public ResponseEntity<Void> deleteRole(@PathVariable("id") Long id,
                                       @RequestParam("roleId") Long roleId) {
        userDtoService.deleteRole(id, roleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
