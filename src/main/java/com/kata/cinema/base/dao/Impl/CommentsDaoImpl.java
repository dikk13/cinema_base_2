package com.kata.cinema.base.dao.Impl;

import com.kata.cinema.base.dao.abstracts.CommentsDao;
import com.kata.cinema.base.models.Comments;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class CommentsDaoImpl extends AbstractDaoImpl<Long, Comments> implements CommentsDao {

    @Override
    public List<Comments> getCommentsListByNewsId(long newsId) {
        return entityManager.
                createQuery("select new com.kata.cinema.base." +
                        "models.Comments(c.id, c.text, c.date, c.user, c.news)" +
                        " from Comments c where c.news.id =: id", Comments.class)
                .setParameter("id", newsId)
                .getResultList();
    }
}
