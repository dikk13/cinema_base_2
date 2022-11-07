package com.kata.cinema.base.webapp.controllers.moderate;

import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.response.CommentsResponseDto;
import com.kata.cinema.base.dto.response.ReviewResponseDto;
import com.kata.cinema.base.mappers.CommentsMapper;
import com.kata.cinema.base.models.enums.ReviewSortType;
import com.kata.cinema.base.models.enums.TypeReview;
import com.kata.cinema.base.service.entity.CommentsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/comments/moderator/")
public class CommentsRestController {

    private final CommentsService commentsService;

    private final CommentsMapper commentsMapper;

    public NewsRestController(CommentsService commentsService, CommentsMapper commentsMapper) {
        this.commentsService = commentsService;
        this.commentsMapper = commentsMapper;
    }

//    @GetMapping("/page/{pageNumber}")
//    public PageDto<CommentsResponseDto> getCommentsResponseDtoByPageId(
//            @PathVariable("pageNumber") long pageNumber,
//            @RequestParam("itemsOnPage") long itemsOnPage
//    ) {
//        return commentsMapper.toDTOList(commentsService.getAllCommentsNoModerate(pageNumber));
//    }

    @GetMapping("/api/movies/{id}/reviews/page/{pageNumber}")
    public PageDto<ReviewResponseDto> getReview
            (@PathVariable("id") Long movieId, @PathVariable("pageNumber") Integer pageNumber,
             @RequestParam(value = "itemsOnPage", required = false, defaultValue = "10") Integer itemsOnPage,
             @RequestParam(value = "typeReview", required = false) TypeReview typeReview,
             @RequestParam(value = "reviewSortType", required = false, defaultValue = "DATE_ASC") ReviewSortType reviewSortType) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("movieId", movieId);
        parameters.put("typeReview", typeReview);
        parameters.put("reviewSortType", reviewSortType);
        return responseDtoService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters);
    }


    @PatchMapping("/{id}/moderator")
    public String update() {
        System.out.println("Project is deleted");
        return "Project is updated";
    }


    @DeleteMapping("comments/{id}")
    public String deleteProject(@PathVariable("page_id") long id) {
        projectRepository.deleteById(id);
        return "redirect:/feed";
    }
}