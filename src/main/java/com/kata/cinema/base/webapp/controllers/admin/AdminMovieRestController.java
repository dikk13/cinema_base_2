package com.kata.cinema.base.webapp.controllers.admin;


import com.kata.cinema.base.dto.request.MovieRequestDto;
import com.kata.cinema.base.mappers.MovieMapper;
import com.kata.cinema.base.service.entity.MovieService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@RestController
@RequestMapping("/api/admin/movies")
@RequiredArgsConstructor
public class AdminMovieRestController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @PostMapping("/{id}/uploadPreview")
    public ResponseEntity<String> upload(@PathVariable(name = "id") Long id, @RequestParam(name = "file") MultipartFile file) {

        String location = "uploads";
        String location2 = "movies";
        String location3 = "preview";

        File pathFile = new File(location);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        File nestedPath1 = new File(pathFile, location2);
        if (!nestedPath1.exists()) {
            nestedPath1.mkdirs();
        }

        File nestedPath2 = new File(nestedPath1, location3);
        if (!nestedPath2.exists()) {
            nestedPath2.mkdirs();
        }

        if (!file.isEmpty()) {
            try {
                File fileNew = new File(location + File.separator + location2 + File.separator + location3 + File.separator + id.toString() + ".png");

                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(fileNew));

                stream.write(bytes);
                stream.close();

                return ResponseEntity.ok("Файл " + fileNew.getName() + " успешно загружен в " + fileNew.getPath());
            } catch (Exception e) {
                return ResponseEntity.ok("Не удалось загрузить файл: " + e.getMessage());
            }
        } else {
            return ResponseEntity.ok("Не удалось загрузить файл: файл пустой");
        }
    }

    @PostMapping
    public ResponseEntity<Void> addNewMovie(@RequestBody MovieRequestDto movieRequestDto) {
        movieService.create(movieMapper.toMovie(movieRequestDto));

        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMovie(@PathVariable Long id, @RequestBody MovieRequestDto movieRequestDto) {
        movieService.updateById(id, movieMapper.toMovie(movieRequestDto));
        return ResponseEntity.ok(null);
    }


}



