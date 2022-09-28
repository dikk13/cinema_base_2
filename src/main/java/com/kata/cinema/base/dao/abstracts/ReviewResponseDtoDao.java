package com.kata.cinema.base.dao.abstracts;
import com.kata.cinema.base.dto.ReviewResponseDto;
import com.kata.cinema.base.enums.ReviewSortType;
import com.kata.cinema.base.enums.TypeReview;

import java.util.Map;


public interface ReviewResponseDtoDao extends PaginationDtoDao<ReviewResponseDto> {
    String sortingByTypeReview(TypeReview typeReview);
    String sortingByReviewSortType(ReviewSortType reviewSortType);

}
