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
    public PageDto<ReviewResponseDto> getReviewResponseDto(Long movieID,Long count) {
       List<ReviewResponseDto> resultList =entityManager.createQuery("select new com.kata.cinema.base.dto.ReviewResponseDto(r.id,r.typeReview,r.title)" +
                       " from Review r where r.id =: movieID", ReviewResponseDto.class).setParameter("movieID",movieID).getResultList();
        PageDto<ReviewResponseDto> pageDto = new PageDto<>(count,resultList);
        return pageDto;

    }
}
