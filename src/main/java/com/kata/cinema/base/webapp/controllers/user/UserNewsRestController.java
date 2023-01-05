package com.kata.cinema.base.webapp.controllers.user;

import com.kata.cinema.base.dto.request.CommentsRequestDto;
import com.kata.cinema.base.exception.CommentNotFoundException;
import com.kata.cinema.base.mappers.CommentsMapper;

import com.kata.cinema.base.models.News;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.service.entity.CommentsService;
import com.kata.cinema.base.service.entity.UserService;
import lombok.AllArgsConstructor;
import org.mapstruct.ap.shaded.freemarker.core.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user/news")
@AllArgsConstructor
public class UserNewsRestController {

    private final CommentsService commentsService;
    private final CommentsMapper commentsMapper;
    private final UserService userService;

    @PostMapping("/{id}/comments")
    public ResponseEntity<Void> addComments(@PathVariable("id") long newsId,
                                     @AuthenticationPrincipal User principal,
                                     @RequestBody CommentsRequestDto commentsRequestDto) {
        Comment comment = commentsMapper.toComments(commentsRequestDto);
        String username = principal.getEmail();
        Optional<User> userOptional = userService.getByEmail(username);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Неверно передан id, пользователя с таким id нету ");
        }
        News news = new News();
        news.setId(newsId);
        comment.setUser(userOptional.get());
        comment.setNews(news);
        commentsService.create(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long commentId,
                                                  @AuthenticationPrincipal User currentUser) {
       Optional<Comment> commentOptional = commentsService.getById(commentId);
       if (commentOptional.isEmpty()) throw new CommentNotFoundException("Comment with id is not found");
       Comment comment = commentOptional.get();
       if (comment.getUser().getId().longValue() != currentUser.getId().longValue()) {
           throw new RuntimeException("Allowed to delete users own comments only");
       }
       commentsService.delete(comment);
       return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<Void> updateComment(@PathVariable("id") Long commentId,
                                              @RequestBody CommentsRequestDto commentsRequestDto,
                                              @AuthenticationPrincipal User currentUser) {
        Optional<Comment> commentOptional = commentsService.getById(commentId);
        if (commentOptional.isEmpty()) throw new CommentNotFoundException("Comment with id is not found");
        Comment commentToUpdate = commentOptional.get();
        if (commentToUpdate.getUser().getId().longValue() != currentUser.getId().longValue()) {
            throw new RuntimeException("Allowed to update users own comments only");
        }
        Comment commentFromDto = commentsMapper.toComments(commentsRequestDto);
        commentToUpdate.setMessage(commentFromDto.getMessage());
        commentToUpdate.setDate(commentFromDto.getDate());
        commentToUpdate.setLevel(commentFromDto.getLevel());
        commentToUpdate.setParentId(commentFromDto.getParentId());
        commentsService.update(commentToUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
