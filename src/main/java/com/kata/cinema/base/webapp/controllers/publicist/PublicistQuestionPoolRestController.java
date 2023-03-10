package com.kata.cinema.base.webapp.controllers.publicist;

import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.request.QuestionRequestDto;
import com.kata.cinema.base.dto.response.QuestionResponseDto;
import com.kata.cinema.base.mappers.QuestionMapper;
import com.kata.cinema.base.models.News;
import com.kata.cinema.base.models.Question;
import com.kata.cinema.base.service.dto.QuestionResponseDtoService;
import com.kata.cinema.base.service.entity.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/publicist")
@AllArgsConstructor
public class PublicistQuestionPoolRestController {

    private final QuestionService questionService;

    private final QuestionMapper questionMapper;

    private QuestionResponseDtoService questionResponseDtoService;

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("id") Long questionId) {
        Optional<Question> question = questionService.getById(questionId);
        Long news_id = question.orElseThrow().getNews().getId();
        questionService.deleteQuestionWithAnswersAndResults(news_id, questionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/news/{id}/questions")
    public ResponseEntity<Void> addNewQuestion(@PathVariable("id") Long newsId,
                                               @RequestBody QuestionRequestDto questionRequestDto){
        Question newQuestion = questionMapper.toQuestion(questionRequestDto);
        News news = questionResponseDtoService.findNews(newsId);
        newQuestion.setNews(news);
        questionService.create(newQuestion);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/questions/page/{pageNumber}")
    public ResponseEntity<PageDto<QuestionResponseDto>> getQuestionPage(@PathVariable("pageNumber") Integer pageNumber,
                                                                        @RequestParam(value = "itemsOnPage", required = false, defaultValue = "10") Integer itemsOnPage) {
        return ResponseEntity.ok(questionResponseDtoService.getPageDto(pageNumber, itemsOnPage));
    }
}
