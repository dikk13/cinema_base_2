package com.kata.cinema.base.service.entity.Impl;

import com.kata.cinema.base.dao.entity.AbstractDao;
import com.kata.cinema.base.dao.entity.RatingCommentDao;
import com.kata.cinema.base.models.RatingComment;
import com.kata.cinema.base.models.enums.TypeRating;
import com.kata.cinema.base.service.entity.AbstractServiceImpl;
import com.kata.cinema.base.service.entity.CommentsService;
import com.kata.cinema.base.service.entity.RatingCommentService;
import com.kata.cinema.base.service.entity.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingCommentServiceImpl extends AbstractServiceImpl<Long, RatingComment> implements RatingCommentService {

    private final RatingCommentDao ratingCommentDao;
    private final UserService userService;
    private final CommentsService commentsService;

    public RatingCommentServiceImpl(AbstractDao<Long, RatingComment> abstractDao, RatingCommentDao ratingCommentDao,
                                    UserService userService, CommentsService commentsService) {
        super(abstractDao);
        this.ratingCommentDao = ratingCommentDao;
        this.userService = userService;
        this.commentsService = commentsService;
    }

    @Override
    public void createOrUpdateRatingComment(Long userId, Long commentId, TypeRating rating) {
        Optional<RatingComment> ratingCommentOptional = ratingCommentDao.getByUserIdCommentId(userId, commentId);
        if (ratingCommentOptional.isEmpty()) {
            RatingComment ratingComment = new RatingComment();
            ratingComment.setRating(rating);
            ratingComment.setUser(userService.getById(userId).get());
            ratingComment.setComments(commentsService.getById(commentId).get());
            ratingCommentDao.create(ratingComment);
        } else {
            RatingComment ratingComment = ratingCommentOptional.get();
            ratingComment.setRating(rating);
            ratingCommentDao.update(ratingComment);
        }
    }
}
