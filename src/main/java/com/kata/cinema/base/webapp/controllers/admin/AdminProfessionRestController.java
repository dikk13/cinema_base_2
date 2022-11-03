package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.exception.ProfessionIsBeingUsedException;
import com.kata.cinema.base.mappers.ProfessionMapper;
import com.kata.cinema.base.models.MoviePerson;
import com.kata.cinema.base.models.Profession;
import com.kata.cinema.base.service.entity.MoviePersonService;
import com.kata.cinema.base.service.entity.ProfessionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/professions")
@AllArgsConstructor
public class AdminProfessionRestController {

    private final ProfessionService professionService;
    private final ProfessionMapper professionMapper;
    private final MoviePersonService moviePersonService;

    @PostMapping("/")
    public void addNewProfession(@RequestParam(name = "string") String string) {
        professionService.create(professionMapper.toProfessionFromString(string));
    }

    @PutMapping("/{id}")
    public void updateProfession(@PathVariable Long id, @RequestParam(name = "string") String string) {
        professionService.updateById(id, string);
    }

    @DeleteMapping("/{id}")
    public void deleteProfessionById(@PathVariable Long id) {
        List<MoviePerson> moviePersonList = moviePersonService.getAll();
        for (MoviePerson moviePerson : moviePersonList) {
            if (moviePerson.getProfession().equals(professionService.getById(id).get())) {
                throw new ProfessionIsBeingUsedException("Невозможно удалить выбранную провессию" +
                        ", так как её уже используют.");
            } else {
                professionService.deleteById(id);
            }

        }
    }

}
