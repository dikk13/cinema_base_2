package com.kata.cinema.base.service.dto.impl;

import com.kata.cinema.base.dao.dto.CriticResponseDtoDao;
import com.kata.cinema.base.dto.request.CriticReviewRequestDto;
import com.kata.cinema.base.dto.response.CriticReviewResponseDto;
import com.kata.cinema.base.mappers.CriticalReviewMapper;
import com.kata.cinema.base.models.CriticalReview;
import com.kata.cinema.base.service.dto.CriticResponseDtoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CriticResponseDtoServiceImpl implements CriticResponseDtoService {

    private final CriticResponseDtoDao criticResponseDtoDao;
    private final CriticalReviewMapper mapper;

    public CriticResponseDtoServiceImpl(CriticResponseDtoDao criticResponseDtoDao, CriticalReviewMapper mapper) {
        this.criticResponseDtoDao = criticResponseDtoDao;
        this.mapper = mapper;
    }

    @Override
    public CriticReviewResponseDto getCriticResponseDtoById(Long id) {
        return criticResponseDtoDao.getCriticResponseDtoById(id);
    }

    @Override
    public List<CriticReviewResponseDto> getAllReviews() {
        return criticResponseDtoDao.getAllReviews();
    }

    @Override
    public Optional<CriticalReview> getById(Long id) {
        return criticResponseDtoDao.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        criticResponseDtoDao.deleteById(id);
    }

    @Override
    public void updateById(Long id, CriticReviewRequestDto criticReviewRequestDto) {
        Optional<CriticalReview> reviewContainer = getById(id);
        if(reviewContainer.isPresent()) {
            CriticalReview newReview = mapper.toCriticalReview(criticReviewRequestDto);
            CriticalReview review = reviewContainer.get();
            review.setMovie(newReview.getMovie());
            review.setId(newReview.getId());
            review.setCritic(newReview.getCritic());
            review.setTypeReview(newReview.getTypeReview());
            review.setScore(newReview.getScore());
            review.setDescription(newReview.getDescription());
            review.setTitle(newReview.getTitle());
            criticResponseDtoDao.update(review);
        } else {
            throw new RuntimeException("Critical Review with id = "+id+" was not found ") {};
        }
    }
}
