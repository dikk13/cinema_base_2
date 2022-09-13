package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.ReviewResponseDtoDao;
import com.kata.cinema.base.dto.PageDto;
import com.kata.cinema.base.dto.ReviewResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ReviewResponseDtoDaoImpl implements ReviewResponseDtoDao {

    @PersistenceContext
    protected EntityManager entityManager;
    @Override
    public PageDto<List<ReviewResponseDto>> getReviewResponseDto() {


entityManager.createQuery("select new com.kata.cinema.base.dto.ReviewResponseDto(r.typeReview) from Review r", ReviewResponseDto.class).getResultList();
        return null;

    }
}
