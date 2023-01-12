package com.kata.cinema.base.webapp.controllers.unauthorized;

import com.kata.cinema.base.dto.response.CommentsResponseDto;
import com.kata.cinema.base.mappers.CommentsMapper;
import com.kata.cinema.base.models.Comment;
import com.kata.cinema.base.service.entity.CommentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class CommentsRestController {

    private final CommentsResponseDto commentsResponseDto;
    private final CommentsService commentsService;
    private final CommentsMapper commentsMapper;


    public CommentsRestController(CommentsResponseDto commentsResponseDto,
                                  CommentsService commentsService,
                                  CommentsMapper commentsMapper) {
        this.commentsResponseDto = commentsResponseDto;
        this.commentsService = commentsService;
        this.commentsMapper = commentsMapper;
    }

//    @GetMapping("/comments/moderator/page/{pageNumber}")
//    public PageDto<CommentsResponseDto> getCommentsResponseDtoByPageId(
//            @PathVariable("pageNumber") int pageNumber,
//            @RequestBody CommentsResponseDto commentsResponseDto,
//            @RequestParam("itemsOnPage") int itemsOnPage
//    ) {
//        Map<String, Object> parameters = new HashMap<>();
//        if (!commentsResponseDto.getIsModerate()) {
//            parameters.put("pageNumber", pageNumber);
//            parameters.put("itemsOnPage", itemsOnPage);
//        }
//        return commentsResponseDtoService.getPageDtoWithParameters(pageNumber, itemsOnPage, parameters);
//
//    }

    @PatchMapping("/comments/{id}/moderator")
    public ResponseEntity<Void> updateIsModerate(@RequestBody CommentsResponseDto commentsResponseDto) {

        Comment commentUpdate = commentsMapper.toCommentsResp(commentsResponseDto);
        commentUpdate.setIsModerate(true);
        commentsService.update(commentUpdate);
        return new ResponseEntity<>(HttpStatus.OK);

    }


    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable("id") Long id) {
        commentsService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}