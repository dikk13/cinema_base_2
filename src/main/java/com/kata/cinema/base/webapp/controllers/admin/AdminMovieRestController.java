package com.kata.cinema.base.webapp.controllers.admin;


import com.kata.cinema.base.dto.request.AvailableOnlineMovieRequestDto;
import com.kata.cinema.base.mappers.AvailableOnlineMovieMapper;
import com.kata.cinema.base.models.AvailableOnlineMovie;
import com.kata.cinema.base.service.entity.AvailableOnlineMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/movies")
@RequiredArgsConstructor
public class AdminMovieRestController {
    private final AvailableOnlineMovieService availableOnlineMovieService;
    private final AvailableOnlineMovieMapper availableOnlineMovieMapper;

    @PostMapping("/{id}/uploadPreview")
    public String upload(@PathVariable(name = "id") Long id, @RequestParam(name = "file") MultipartFile file) {

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

                return "Файл " + fileNew.getName() + " успешно загружен в " + fileNew.getPath();
            } catch (Exception e) {
                return "Не удалось загрузить файл: " + e.getMessage();
            }
        } else {
            return "Не удалось загрузить файл: файл пустой";
        }
    }
    @PostMapping("/api/admin/movies/{id}/online")
    public void availableOnlineMovieRequestDto (@PathVariable("id") Long id,
                                                @RequestBody AvailableOnlineMovieRequestDto
                                                        availableOnlineMovieRequestDto) {
        Optional<AvailableOnlineMovie> availableOnlineMovie1=
                availableOnlineMovieService.getById(id);

        if (availableOnlineMovie1.isPresent()){
            availableOnlineMovie1.get();
            availableOnlineMovieService.create(availableOnlineMovieMapper.toAvailableOnlineMovie(availableOnlineMovieRequestDto));
        } else {
            throw new NullPointerException();
        }
    }
    @PatchMapping("/api/admin/movies/{id}/online/deactivate")
    public boolean deactivate(@PathVariable("id") Long id,@RequestBody AvailableOnlineMovie availableOnlineMovie) {
        Optional<AvailableOnlineMovie> availableOnlineMovieDeactivate=availableOnlineMovieService.getById(id);
        availableOnlineMovie.setEnabled(false);
        return false;
    }
    @PatchMapping("/api/admin/movies/{id}/online/activate")
    public boolean activate (@PathVariable("id") Long id,@RequestBody AvailableOnlineMovie availableOnlineMovie){
        Optional<AvailableOnlineMovie> availableOnlineMovieActivate=availableOnlineMovieService.getById(id);
        availableOnlineMovie.setEnabled(true);
        return true;
    }


}



