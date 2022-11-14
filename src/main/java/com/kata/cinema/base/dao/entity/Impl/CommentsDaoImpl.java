package com.kata.cinema.base.dao.entity.Impl;

import com.kata.cinema.base.dao.entity.CommentsDao;
import com.kata.cinema.base.models.Comment;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class CommentsDaoImpl extends AbstractDaoImpl<Long, Comment> implements CommentsDao {

    @Override
    public List<Comment> getCommentsListByNewsId(long newsId) {
        return entityManager.
                createQuery("select c from Comment c where c.news.id =: id", Comment.class)
                .setParameter("id", newsId)
                .getResultList();
    }
}
