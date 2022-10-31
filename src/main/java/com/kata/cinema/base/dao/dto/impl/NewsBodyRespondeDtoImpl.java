package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.NewsBodyResponseDtoDao;
import com.kata.cinema.base.dto.response.FolderMovieResponsDto;
import com.kata.cinema.base.dto.response.NewsBodyResponseDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class NewsBodyRespondeDtoImpl implements NewsBodyResponseDtoDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public NewsBodyResponseDto getNewsBodyResponseDtoByNewsId(Long id) {
        return (NewsBodyResponseDto) entityManager.createQuery("SELECT NEW com.kata.cinema.base.dto.response.NewsBodyResponseDto(n.id, n.rubric, n.date, n.title, n.htmlBody, n.questions) FROM News n WHERE n.id = :id", NewsBodyResponseDto.class)
    }
}
//
//    public FolderMovieResponsDto getFolderMovieResponsDtoById(Long folderMovieId) {
//        return entityManager.createQuery("select new com.kata.cinema.base.dto.response.FolderMovieResponsDto(fm.id, fm.category, fm.privacy, fm.name, fm.description) from FolderMovie fm where fm.id =: id", FolderMovieResponsDto.class)
//                .setParameter("id", folderMovieId)
//                .getSingleResult();
//    }
