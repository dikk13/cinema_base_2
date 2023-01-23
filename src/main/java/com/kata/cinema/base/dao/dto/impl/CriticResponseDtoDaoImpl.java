package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.CriticResponseDtoDao;
import com.kata.cinema.base.dto.response.CriticReviewResponseDto;
import com.kata.cinema.base.models.CriticalReview;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class CriticResponseDtoDaoImpl implements CriticResponseDtoDao {

    private final EntityManager entityManager;

    public CriticResponseDtoDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public CriticReviewResponseDto getCriticResponseDtoById(Long id) {
        return (CriticReviewResponseDto) entityManager.createQuery("select new com.kata.cinema.base.dto.response.CriticReviewResponseDto("+
                "r.id,r.typeReview,r.title,r.description,r.score)" +
                " from CriticalReview r where r.id =: id")
                .setParameter("id",id)
                .getSingleResult();
    }

    @Override
    public List<CriticReviewResponseDto> getAllReviews() {
        return entityManager.createQuery("select new com.kata.cinema.base.dto.response.CriticReviewResponseDto("+
                "r.id,r.typeReview,r.title,r.description,r.score)" +
                " from CriticalReview r ")
                .getResultList();
    }

    @Override
    public Optional<CriticalReview> getById(Long id) {
        return (CriticalReview) entityManager.createQuery("select new com.kata.cinema.base.dto.response.CriticReviewResponseDto("+
                "r.id,r.typeReview,r.title,r.description,r.score)" +
                " from CriticalReview r where r.id =: id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void deleteById(Long id) {
        entityManager.createQuery("delete from CriticalReview r where r.id =: id")
                .setParameter("id",id)
                .executeUpdate();
    }
}
