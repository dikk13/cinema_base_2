package com.kata.cinema.base.webapp.controllers.user;

import com.kata.cinema.base.models.ReactionReview;
import com.kata.cinema.base.models.Review;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.models.enums.TypeRating;
import com.kata.cinema.base.service.entity.ReactionReviewService;
import com.kata.cinema.base.service.entity.ReviewService;
import com.kata.cinema.base.service.entity.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/reviews")
@AllArgsConstructor
public class UserReviewsRestController {

    private final ReactionReviewService reactionReviewService;
    private final ReviewService reviewService;

    @PostMapping("/{id}")
    public void addReactionReview(@PathVariable("id") Long reviewId,
                                  @RequestParam(value = "TypeRating") String rating,
                                  @AuthenticationPrincipal User currentUser) {

        Review review = reviewService.getAll().get((int)(reviewId - 1));

        Optional<ReactionReview> reactionReview = reactionReviewService.getReactionReviewByUserIdAndReviewId(currentUser.getId(), reviewId);

        if (reactionReview.isEmpty()) {
            ReactionReview newReactionReview = new ReactionReview();
            newReactionReview.setUser(currentUser);
            newReactionReview.setReview(review);
            newReactionReview.setRating(TypeRating.valueOf(rating.toUpperCase()));
            reactionReviewService.create(newReactionReview);
        } else {
            reactionReview.get().setRating(TypeRating.valueOf(rating.toUpperCase()));
            reactionReviewService.update(reactionReview.get());
        }



    }
}
