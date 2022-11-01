package com.kata.cinema.base.dao.dto.impl;

import com.kata.cinema.base.dao.dto.NewsBodyResponseDtoDao;
import com.kata.cinema.base.dto.response.MovieViewResponseDto;
import com.kata.cinema.base.dto.response.NewsBodyResponseDto;
import com.kata.cinema.base.models.Question;
import com.kata.cinema.base.models.enums.Rubric;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public class NewsBodyResponseDtoDaoImpl implements NewsBodyResponseDtoDao {

    private final EntityManager entityManager;

    public NewsBodyResponseDtoDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public NewsBodyResponseDto getNewsBodyResponseDtoByNewsId(Long newsId) {
        return entityManager.createQuery(
                        "SELECT NEW com.kata.cinema.base.dto.response.NewsBodyResponseDto (n.id, n.rubric, " +
                                "n.date, n.title, n.htmlBody, " +
                                "(SELECT COUNT (c) FROM Comment c WHERE c.news.id = :newsId), " +
                                "CONCAT (n.user.first_name, ' ', n.user.last_name))" +
                                "FROM News n WHERE n.id = :newsId", NewsBodyResponseDto.class)
                .setParameter("newsId", newsId).getSingleResult();
    }
}
//    public NewsBodyResponseDto(Long id, Rubric rubric, LocalDateTime date,
//                               String title, String htmlBody, Collection<Question> questions,
//                               Long countComment, String authorName) {
//        this.id = id;
//        this.rubric = rubric;
//        this.date = date;
//        this.title = title;
//        this.htmlBody = htmlBody;
//        this.questions = questions.stream().toList();
//        this.countComment = countComment.intValue();
//        this.authorName = authorName;
//    }
