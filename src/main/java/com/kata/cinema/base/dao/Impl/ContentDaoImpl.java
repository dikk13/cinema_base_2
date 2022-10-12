package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.ContentDao;
import com.kata.cinema.base.models.Content;
import com.kata.cinema.base.models.enums.ContentType;
import org.springframework.stereotype.Repository;

@Repository
public class ContentDaoImpl extends AbstractDaoImpl<Long, Content> implements ContentDao {
    @Override
    public String getContentByMovieIdAndContentType(Long movieId, ContentType type) {
        return (String) entityManager.createQuery("select c.content_url from Content c where c.movie.id =: id and c.type =: type")
                .setParameter("id", movieId)
                .setParameter("type", type)
                .getSingleResult();
    }
}
