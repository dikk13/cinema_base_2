package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.dto.request.CollectionRequestDto;
import com.kata.cinema.base.dto.response.CollectionResponseDto;
import com.kata.cinema.base.exception.CollectionNotFoundException;
import com.kata.cinema.base.mappers.CollectionMapper;
import com.kata.cinema.base.models.enums.CollectionType;
import com.kata.cinema.base.service.dto.CollectionResponseDtoService;
import com.kata.cinema.base.service.entity.CollectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collections")
@AllArgsConstructor
public class CollectionsRestController {

    private final CollectionMapper collectionMapper;
    private final CollectionService collectionService;

    private final CollectionResponseDtoService collectionResponseDtoService;


    @GetMapping()
    public ResponseEntity<List<CollectionResponseDto>> getCollectionsByCollectionType(
            @RequestParam(defaultValue = "MOVIES") CollectionType collectionType) {
        return new ResponseEntity<>(collectionResponseDtoService.getCollectionResponseDtoListByType(collectionType), HttpStatus.FOUND);
    }

    @PostMapping()
    public ResponseEntity<Void> addNewCollection(@RequestBody CollectionRequestDto collectionRequestDto) {
        collectionService.create(collectionMapper.toCollection(collectionRequestDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCollection(@RequestBody CollectionRequestDto collectionRequestDto,
                                                 @PathVariable("id") Long id) {
        if (collectionService.existById(id)) {
            collectionService.update(collectionMapper.toCollection(collectionRequestDto));
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new CollectionNotFoundException("Collection with this ID doesn't exist");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteCollection(@PathVariable("id") Long id) {
        if (collectionService.existById(id)) {
            collectionService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new CollectionNotFoundException("Collection with this ID doesn't exist");
        }
    }
}
