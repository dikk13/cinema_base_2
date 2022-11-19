package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.dto.response.ChapterResponseDto;
import com.kata.cinema.base.models.Chapter;
import com.kata.cinema.base.service.dto.ChapterResponseDtoService;
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

    private final ChapterResponseDtoService chapterResponseDtoService;

    @GetMapping
    public ResponseEntity<List<ChapterResponseDto>> getAllChapters() {
        List<ChapterResponseDto> chapters = chapterResponseDtoService.getChapterResponseDtoList();
        return ResponseEntity.ok(chapters);
    }
}
