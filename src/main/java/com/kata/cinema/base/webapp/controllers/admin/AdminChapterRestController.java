package com.kata.cinema.base.webapp.controllers.admin;

import com.kata.cinema.base.models.Chapter;
import com.kata.cinema.base.service.entity.ChapterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin/chapters")
@AllArgsConstructor
public class AdminChapterRestController {

    private final ChapterService chapterService;

    @PostMapping
    public ResponseEntity<Void> addNewChapter(@RequestParam(name = "string") String string) {
        Chapter chapter = new Chapter();
        chapter.setName(string);
        chapterService.create(chapter);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeChapter(@PathVariable Long id) {
        Optional<Chapter> chapterToDelete = chapterService.getById(id);
        if (chapterToDelete.isPresent()) {
            chapterService.deleteById(id);
            return ResponseEntity.ok(null);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateChapter(@PathVariable Long id, @RequestParam(name = "string") String string) {
        chapterService.updateById(id, string);
        return ResponseEntity.ok(null);
    }


}
