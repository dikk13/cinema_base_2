package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.models.Chapter;
import com.kata.cinema.base.service.entity.ChapterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chapters")
@AllArgsConstructor
public class ChapterRestController {

    private final ChapterService chapterService;

    @GetMapping
    public ResponseEntity<List<Chapter>> getAllChapters() {
        List<Chapter> chapters = chapterService.getAll();
        return ResponseEntity.ok(chapters);
    }
}
